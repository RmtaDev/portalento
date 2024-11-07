// funcion para insertar un habilidad demandada
function insertarHabilidDemandada() {
	const boton = document.getElementById("botonInsertarDemandada");

	boton.addEventListener("click", function() {
		console.log("haz tocado el boton de insertar");
		const idCategoria = document.getElementById("demandadas").value;
		const habilidadDemandada = document.getElementById("textoDemandada").value;


		if (idCategoria && habilidadDemandada) {
			console.log(`Has insertado : Categoria = ${idCategoria}, Texto = ${habilidadDemandada}`);

			//Crear la URL con parametros de consulta
			const url = `InsertarHabilidadesDemandadas?habilidadDemandada=${encodeURIComponent(habilidadDemandada)}&idCategoria=${encodeURIComponent(idCategoria)}`;

			// Enviar los datos al servidor usando fetch
			fetch(url, {
				method: "POST",
				
			})
				.then(response => {
					if (response.ok) {
						return response.json();
					} else {
						throw new Error("Error en la respuesta del servidor");
					}
				})
				.then(data => {
					console.log("respuesta del servidor: ", data);
					
					let divPadre = document.getElementById("container-hb-demandadas");
					pintarHabilidadOfertada (data, divPadre);
					
					const selectCategoria = document.getElementById("demandadas");
					selectCategoria.selectedIndex = 0;
					
					document.getElementById("textoDemandada").value = "";
					
				})
				.catch(error => {
					console.error("Hubo un problema con la solicitud: ", error);
					alert("Error al insertar la hibilidad. Intentelo de nuevo.");
				})

		}

		else {
			console.log("por favor, completa todos los campos.");
			alert("Por favor, completa todos los campos antes de insertar.");
		}
	});
}

document.addEventListener("DOMContentLoaded", insertarHabilidDemandada);