window.addEventListener('load', function () {

    (function(){
        const url = '/odontologos';
        const settings = {
            method: 'GET'
        }

        fetch(url,settings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la respuesta del servidor: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                console.log(data);

                for( let odontologo of data){

                var table = document.getElementById("odontologoTable");
                var odontologoRow = table.insertRow();
                let tr_id = 'tr_' + odontologo.id;
                odontologoRow.id = tr_id;

                let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                      ' type="button" onclick="deleteOd('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

                //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
                //a la función de java script findBy que se encargará de buscar la pelicula que queremos
                //modificar y mostrar los datos de la misma en un formulario.
                let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                      ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                                      odontologo.id +
                                      '</button>';

                //armamos cada columna de la fila
                //como primer columna pondremos el boton modificar
                //luego los datos de la pelicula
                //como ultima columna el boton eliminar
                odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
                    '<td>' + deleteButton + '</td>';
            };
        })
    })

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "../get_odontologos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })

})
