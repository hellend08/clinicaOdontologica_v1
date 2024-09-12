function deleteBy(id) {
  const url = '/odontologos/' + id; // Construimos la URL de la API para eliminar el odontólogo
  const settings = {
    method: 'DELETE' // Usamos el método DELETE para eliminar
  };

  // Hacemos la solicitud DELETE
  fetch(url, settings)
    .then(response => {
      if (!response.ok) {
        throw new Error('Error en la eliminación: ' + response.status);
      }
      return response.json();
    })
    .then(() => {
      // Eliminamos la fila de la tabla correspondiente al odontólogo eliminado
      const rowId = 'tr_' + id;
      const row = document.getElementById(rowId);
      if (row) {
        row.remove(); // Eliminamos la fila del DOM
        console.log(`Odontólogo con ID ${id} eliminado.`);
      }
    })
    .catch(error => {
      console.error('Error al intentar eliminar el odontólogo:', error);
    });
}