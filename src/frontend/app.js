// Connection layer: maps the piece names sent by Java to chess symbols in the browser.
const symbols = {
  white: { king: '♔', queen: '♕', rook: '♖', bishop: '♗', knight: '♘', pawn: '♙' },
  black: { king: '♚', queen: '♛', rook: '♜', bishop: '♝', knight: '♞', pawn: '♟' }
};
const board = document.querySelector('#board');
const status = document.querySelector('#status');
let game, selected = null, validMoves = new Set();
function squareName(row, col) { return String.fromCharCode(97 + col) + (8 - row); }
// Rebuilds the visual board from the latest state returned by the Java backend.
function render() {
  board.replaceChildren();
  game.board.forEach((row, rowIndex) => row.forEach((piece, colIndex) => {
    const square = document.createElement('button');
    const name = squareName(rowIndex, colIndex);
    square.className = `square ${(rowIndex + colIndex) % 2 ? 'dark' : 'light'}${selected === name ? ' selected' : ''}${validMoves.has(name) ? ' valid' : ''}`;
    square.textContent = piece ? symbols[piece.color][piece.type] : '';
    square.setAttribute('aria-label', piece ? `${piece.color} ${piece.type} on ${name}` : `Empty ${name}`);
    square.onclick = () => choose(name, piece);
    board.append(square);
  }));
  status.textContent = `${game.turn === 'w' ? 'White' : 'Black'} to move`;
}
// Selects a start square, then sends the completed move to POST /api/move.
async function choose(square, piece) {
  if (!selected) {
    if (piece && piece.color === (game.turn === 'w' ? 'white' : 'black')) {
      selected = square;
      const response = await fetch(`/api/valid-moves?from=${square}`);
      const result = await response.json();
      validMoves = new Set(result.moves);
      render();
    }
    return;
  }
  if (selected === square) { selected = null; validMoves.clear(); render(); return; }
  const response = await fetch('/api/move', { method: 'POST', headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, body: new URLSearchParams({ from: selected, to: square }) });
  const result = await response.json();
  game = result.game;
  selected = null;
  validMoves.clear();
  render();
  if (result.error) status.textContent = result.error;
}
// Loads the board once when the page opens.
fetch('/api/game').then(response => response.json()).then(data => { game = data; render(); }).catch(() => { status.textContent = 'Unable to connect to the chess server.'; });
