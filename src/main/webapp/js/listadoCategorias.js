window.onload = pedirListadoHabilidades;

function pedirListadoHabilidades()
{
	console.log("Pidiendo datos al servidor . . .");
	fetch("ListadoCategoriasHabilidadesServlet")
	.then(respuesta=> {
		console.log("Respuesta recibida " + respuesta.status);
		return respuesta.json();
		}
		)
	.then(selectCategorias => {
            console.log("Listado de habilidades recibido: " + selectCategorias.length);
            selectCategorias.sort(ordenar);
            selectCategorias = selectCategorias.filter(categorias => {
                return true; 
            });

            let selectHabilidades = document.getElementById("selectHabilidades");

            selectHabilidades.innerHTML = "";

            selectCategorias.forEach(categorias => {
                let option = document.createElement("option");
                option.value = categorias.id; 
                option.textContent = categorias.nombre; 
                selectHabilidades.appendChild(option);
            });
        })
        .catch(error => console.error("Error al pedir el listado de habilidades: ", error));
}

function ordenar(a, b) {
    return a.nombre.localeCompare(b.nombre);
}
