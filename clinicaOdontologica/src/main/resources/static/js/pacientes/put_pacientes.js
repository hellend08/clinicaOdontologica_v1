function actualizarPaciente(id, pacienteData) {
    fetch(`/pacientes/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(pacienteData),
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(err => { throw new Error(err.message); });
        }
        return response.json();
    })
    .then(updatedPaciente => {
        console.log('Paciente actualizado exitosamente:', updatedPaciente);
        // Update the UI or perform any further action if needed
    })
    .catch(error => {
        console.error('Error al actualizar el paciente:', error);
    });
}

window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_paciente');
    const responseDiv = document.querySelector('#response'); // Cache reference to the response div

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        // Get input values
        const pacienteId = document.querySelector('#id').value.trim();
        const nombre = document.querySelector('#nombre').value.trim();
        const apellido = document.querySelector('#apellido').value.trim();
        const cedula = document.querySelector('#cedula').value.trim();
        const fechaIngreso = document.querySelector('#fechaIngreso').value.trim();
        const email = document.querySelector('#email').value.trim();
        const calle = document.querySelector('#calle').value.trim();
        const numero = document.querySelector('#numero').value.trim();
        const localidad = document.querySelector('#localidad').value.trim();
        const provincia = document.querySelector('#provincia').value.trim();

        // Validate input values
        if (!pacienteId || !nombre || !apellido || !cedula || !fechaIngreso || !email || !calle || !numero || !localidad || !provincia) {
            showAlert('Error: Todos los campos son obligatorios.', 'danger');
            return;
        }

        const formData = {
            nombre,
            apellido,
            cedula,
            fechaIngreso,
            email,
            domicilio: {
                calle,
                numero,
                localidad,
                provincia
            }
        };

        // Perform PUT request
        const url = `/pacientes/${pacienteId}`;
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(err => { throw new Error(err.message); });
                }
                return response.json();
            })
            .then(data => {
                showAlert('Paciente actualizado exitosamente', 'success');
                resetUploadForm();
            })
            .catch(error => {
                showAlert('Error al actualizar el paciente: ' + error.message, 'danger');
                resetUploadForm();
            });
    });

    function showAlert(message, type) {
        const alertHTML = `
            <div class="alert alert-${type} alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${message}</strong>
            </div>
        `;
        responseDiv.innerHTML = alertHTML;
        responseDiv.style.display = "block";
    }

    function resetUploadForm() {
        formulario.reset(); // Reset form fields
        document.querySelector('#id').focus(); // Focus on the first input
    }

    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname === "/pacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
