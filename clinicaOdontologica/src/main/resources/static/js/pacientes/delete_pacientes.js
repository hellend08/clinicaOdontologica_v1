function deletePac(id) {
    const url = `/pacientes/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar paciente: ' + response.status);
            }
            alert('Paciente eliminado correctamente');
            removeRow(id);
        })
        .catch(error => {
            console.error('Error al eliminar paciente:', error);
            alert('Ocurrió un error al intentar eliminar el paciente');
        });
}

function removeRow(id) {
    const row = document.getElementById('tr_' + id);
    if (row) {
        row.remove();
    }
}