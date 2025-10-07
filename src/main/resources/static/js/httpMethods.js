// Call the update when the update button is clicked
document.getElementById('update-all-btn').addEventListener('click', () => {
    fetch('/update', {
        method: 'PATCH'
    }).then(() => {
        window.location.reload();
    });
});

// Call delete when the delete button is clicked
Array.from(document.getElementsByClassName('btn-danger')).forEach(button => {
    button.addEventListener('click', (event) => {
        const id = event.target.closest('tr').getAttribute('data-id');
        fetch(`/delete/${id}`, {
            method: 'DELETE'
        }).then(() => {
            window.location.reload();
        });
    });
});