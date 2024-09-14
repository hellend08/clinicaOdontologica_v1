//NO ESTA COMPILANDO BIEN ESTE FILE
window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_odontologo_form');

    window.findBy = function(id) {
        const url = `/odontologos/${id}`;
        const settings = {
            method: 'GET'
        };

        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener los datos del odontólogo.');
                }
                return response.json();
            })
            .then(data => {
                document.querySelector('#id').value = data.id;
                document.querySelector('#nombre').value = data.nombre;
                document.querySelector('#apellido').value = data.apellido;
                document.querySelector('#matricula').value = data.matricula;
            })
            .catch(error => {
                console.error('Error al obtener los datos del odontólogo:', error);
            });
    }

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const id = document.querySelector('#id').value;  // Obtener el valor de ID (readonly)
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };

        const url = `/odontologos/${id}`;  // Usar el ID en la ruta de la URL
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Odontólogo actualizado exitosamente</strong> </div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error al actualizar el odontólogo</strong> </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            });
    });

    function resetUploadForm(){
        document.querySelector('#id').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";
    }
});
