window.addEventListener('load', function () {
    const formulario = document.querySelector('#delete_paciente');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const id = document.querySelector('#id').value;

        if (!id) {
            alert("Por favor ingrese un ID de paciente válido.");
            return;
        }

        const url = '/pacientes/' + id;
        const settings = {
            method: 'DELETE'
        };

        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(err => { throw new Error(err.message); });
                }
                return response.json();
            })
            .then(data => {
                const successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Paciente eliminado exitosamente</strong></div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetDeleteForm();
            })
            .catch(error => {
                const errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error al eliminar el paciente: ' + error.message + '</strong></div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetDeleteForm();
            });
    });

    function resetDeleteForm() {
        document.querySelector('#id').value = "";
    }

    (function() {
        const pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname == "/pacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
