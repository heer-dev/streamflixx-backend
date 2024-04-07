// src/main/resources/static/js/script.js
document.addEventListener('DOMContentLoaded', function() {
    fetchMovies();
});

function fetchMovies() {
    fetch('/media/movies')
        .then(response => response.json())
        .then(data => displayMovies(data))
        .catch(error => console.error('Unable to get movies:', error));
}

function displayMovies(movies) {
    const moviesContainer = document.getElementById('movies');
    if (movies.length > 0) {
        const list = movies.map(movie => 
            `<div>
                <h3>${movie.name}</h3>
                <p>${movie.synopsis}</p>
                <p>Price: $${movie.price}</p>
            </div>`
        ).join('\n');
        moviesContainer.innerHTML = list;
    } else {
        moviesContainer.innerHTML = '<p>No movies found.</p>';
    }
}
