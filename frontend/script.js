document.getElementById('shorten-btn').addEventListener('click', () => {

    const originalUrl = document.getElementById('original-url').value;
    if (!originalUrl) {
        alert('Por favor, introduce una URL.');
        return;
    }

    fetch('http://localhost:8080/shorten', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ originalUrl }),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al acortar la URL');
        }
        return response.text();
    })
    .then(shortUrl => {
        document.getElementById('url').innerText = `Su URL corta es: `;
        document.getElementById('short-url').style.display = 'block';
        document.getElementById('short-url-value').value = shortUrl;
        document.getElementById('short-url-value').dataset.value = shortUrl;
    })
    .catch(error => {
        alert(error);
    });
});

document.getElementById('copy-btn').addEventListener('click', () => {
    const shortUrl = document.getElementById('short-url-value').dataset.value;
    if (shortUrl) {
        navigator.clipboard.writeText(shortUrl)
            .then(() => {
                alert('URL corta copiada al portapapeles!');
            })
            .catch(err => {
                console.error('Error al copiar: ', err);
            });
    }
});