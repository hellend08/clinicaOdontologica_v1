window.addEventListener('load', function () {
    const form = document.querySelector('#delete_turno_form');
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        const id = document.querySelector('#turno_id').value.trim();

        // Verifica que el ID no esté vacío
        if (!id) {
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                             '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                             '<strong>ID del turno no puede estar vacío.</strong></div>';
            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
            return;
        }

        deleteBy(id);
    });
});

function deleteBy(id) {
    const url = `/turnos/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text) });
            }
            return response.text();
        })
        .then(() => {
            let successAlert = '<div class="alert alert-success alert-dismissible">' +
                               '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                               '<strong>Turno eliminado correctamente</strong></div>';

            document.querySelector('#response').innerHTML = successAlert;
            document.querySelector('#response').style.display = "block";

            // Limpiar el campo de entrada
            document.querySelector('#turno_id').value = "";
        })
        .catch(error => {
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                             '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                             '<strong>Error al eliminar el turno: </strong>' + error.message + '</div>';

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
        });
}
