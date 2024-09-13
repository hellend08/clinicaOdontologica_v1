window.addEventListener('load', function () {
    const formulario = document.querySelector('#delete_odontologo_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const id = document.querySelector('#id').value;

        const url = '/odontologos/' + id;
        const settings = {
            method: 'DELETE'
        }

        fetch(url, settings)
            .then(response => {
                if (response.ok) {
                    let successAlert = '<div class="alert alert-success alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong>Odontólogo eliminado exitosamente</strong> </div>';

                    document.querySelector('#response').innerHTML = successAlert;
                    document.querySelector('#response').style.display = "block";
                    resetDeleteForm();
                } else {
                    throw new Error("Error al eliminar el odontólogo");
                }
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>' + error.message + '</strong> </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetDeleteForm();
            })
    });

    function resetDeleteForm(){
        document.querySelector('#id').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname === "/odontologos.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
