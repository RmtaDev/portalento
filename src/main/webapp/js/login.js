function loginServidor() {
	let usuario = document.getElementById("usuario").value;
	let password = document.getElementById("password").value;

	let infousuario = {
		usuario: usuario,
		password: password
	}

	let infousuarioJson = JSON.stringify(infousuario);

	fetch("/Login", {
		method: "POST",
		body: infousuarioJson
	})//12 ACTUALIZO LA INTERFAZ DE USUARIO
		.then(respuesta => {
			console.log("Procesando la vuelta ..");
			switch (respuesta.status) {
				case 200:
					console.log("La autencicación ha ido bien");
					alert("La autencicación ha ido bien");
					window.location.href = "bienvenida.html";
					break;
				case 400:
					console.log("No existe ese usuario passoword");
					alert("No existe ese usuario passoword");
					break;
				case 500:
					console.log("Error en la autenticación");
					alert("Error en la autenticación");
					break;

			}
		})
}