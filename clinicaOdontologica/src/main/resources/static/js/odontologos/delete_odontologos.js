function deleteOd(id) {
    const url = `/odontologos/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar odontólogo: ' + response.status);
            }
            alert('Odontólogo eliminado correctamente');
            removeRow(id);
        })
        .catch(error => {
            console.error('Error al eliminar odontólogo:', error);
            alert('Ocurrió un error al intentar eliminar el odontólogo');
        });
}

function removeRow(id) {
    const row = document.getElementById('tr_' + id);
    if (row) {
        row.remove();
    }
}