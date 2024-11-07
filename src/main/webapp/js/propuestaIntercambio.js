let seleccionada = 0;

window.onload = () => {

	//window.alert("pagina cargada")



	let etiquetas = Array.from(document.getElementsByClassName("etiqueta")); // Obtener todas las etiquetas

	//etiquetas [0].addEventListener("click", marcarSelecionada); 

	etiquetas.forEach(

		(etiqueta) => {

			etiqueta.addEventListener("click", habilidadTocada);

		}

	)

}



function marcarHabilidad(label, div) {

	div.style.backgroundColor = "#b2babb";

	label.style.color = "white";

	div.dataset.marcado = '1';

	label.dataset.marcado = '1';

}



function desmarcarHabilidad(label, div) {

	div.style.backgroundColor = "#FFB74D";

	label.style.color = "black";

	div.dataset.marcado = '0';

	label.dataset.marcado = '0';

}



function habilidadTocada(evento) {

	let label;

	let div;



	//alert("Etiqueta tocada " + evento.target);

	if (seleccionada == 0) {

		console.log("no hay habilidad seleccionada")

		if (evento.target instanceof HTMLDivElement) {

			div = evento.target;

			label = div.firstElementChild;

			console.log("Ha tocado el div");



		} else {

			console.log("Ha tocado el label");

			label = evento.target;

			div = label.parentNode;

		}

		marcarHabilidad(label, div);

		seleccionada = 1;

	} else {

		console.log("Ya hay una seleccionada");

		if (evento.target.dataset.marcado == '1') {

			//hay que desmarcarlo

			if (evento.target instanceof HTMLDivElement) {

				div = evento.target;

				label = div.firstElementChild;

				console.log("Ha tocado el div");



			} else {

				console.log("Ha tocado el label");

				label = evento.target;

				div = label.parentNode;

			}

			desmarcarHabilidad(label, div);

			seleccionada=0;

		}



	}

}





function proponer() {

	//alert("Proponer tocado");

	//Tiene que saber que etiqueta ha elegido el usuario

	// el usuario no selecciona nada mostrar aviso 

	// Y mandarla al servidor

}