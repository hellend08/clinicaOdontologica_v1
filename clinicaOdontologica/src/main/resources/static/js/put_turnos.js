window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        let turnoId = document.querySelector('#turno_id').value;

        const formData = {
            id: turnoId,
            fecha: document.querySelector('#fecha').value,
            paciente: { id: document.querySelector('#pacienteId').value },
            odontologo: { id: document.querySelector('#odontologoId').value }
        };

        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let successAlert = '<div class="alert alert-success alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong> Turno actualizado </strong></div>';

            document.querySelector('#response').innerHTML = successAlert;
            document.querySelector('#response').style.display = "block";
        })
        .catch(error => {
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong> Error intente nuevamente</strong> </div>';

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
        })
    })
 })

function findBy(id) {
    const url = '/turnos/'+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let turno = data;
        document.querySelector('#turno_id').value = turno.id;
        document.querySelector('#fecha').value = turno.fecha;
        document.querySelector('#pacienteId').value = turno.paciente.id;
        document.querySelector('#odontologoId').value = turno.odontologo.id;
        document.querySelector('#div_turno_updating').style.display = "block";
    }).catch(error => {
        alert("Error: " + error);
    })
}