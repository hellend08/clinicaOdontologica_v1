function deleteTur(id) {
    const url = `/turnos/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar turno: ' + response.status);
            }
            alert('Turno eliminado correctamente');
            removeRow(id);
        })
        .catch(error => {
            console.error('Error al eliminar turno:', error);
            alert('Ocurrió un error al intentar eliminar el turno');
        });
}

function removeRow(id) {
    const row = document.getElementById('tr_' + id);
    if (row) {
        row.remove();
    }
}