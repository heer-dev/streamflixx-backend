// src/main/resources/static/js/script.js
document.addEventListener('DOMContentLoaded', function() {
    setupEndpointLinks();
});

function setupEndpointLinks() {
    const endpoints = [
        { name: 'Media', path: '/media' },
        { name: 'Customer', path: '/customers' }
        // Add more endpoints as needed
    ];

    const list = document.getElementById('endpoint-list');
    endpoints.forEach(endpoint => {
        let listItem = document.createElement('li');
        let link = document.createElement('a');
        link.textContent = endpoint.name;
        link.href = '#';
        link.onclick = function() {
            fetchAndDisplayData(endpoint.path);
            return false;
        };
        listItem.appendChild(link);
        list.appendChild(listItem);
    });
}

function fetchAndDisplayData(path) {
    fetch(path)
        .then(response => response.json())
        .then(data => displayData(data))
        .catch(error => {
            console.error('Error fetching data:', error);
            displayError('Failed to load data.');
        });
}

function displayData(data) {
    const displayDiv = document.getElementById('data-display');
    displayDiv.innerHTML = ''; // Clear previous content
    if (Array.isArray(data)) {
        data.forEach(item => {
            const itemDiv = document.createElement('div');
            itemDiv.textContent = JSON.stringify(item); // Customize as needed
            displayDiv.appendChild(itemDiv);
        });
    } else {
        displayDiv.textContent = JSON.stringify(data); // Customize as needed
    }
}

function displayError(message) {
    const displayDiv = document.getElementById('data-display');
    displayDiv.textContent = message;
}
