// Ruta del servlet
const baseUrl = 'http://localhost:8080/ejemploServlet/libros-servlet';

// Función para obtener todos los libros
function getBooks() {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `${baseUrl}?operacion=getAllLibros`, true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            const booksContainer = document.getElementById('caja-resultado-libros');
            booksContainer.innerHTML = '';

            data.forEach(book => {
                const bookElement = document.createElement('div');
                bookElement.innerHTML = `
                    <h3>${book.titulo} (${book.isbn})</h3>
                    <p>Autor: ${book.autor}</p>
                `;
                booksContainer.appendChild(bookElement);
            });
        } else {
            console.error('Error:', xhr.statusText);
        }
    };

    xhr.send();
}