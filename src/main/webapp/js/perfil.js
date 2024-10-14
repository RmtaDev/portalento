function obtenerPerfilUsuario() {
	fetch('/obtenerPerfilUsuario', {
		method: 'GET'
	})
		.then(response => response.json())
		.then(data => {

			document.getElementById('nombreUsuario').value = data.nombreUsuario;
			document.getElementById('nombre').value = data.nombre;
			document.getElementById('apellidos').value = data.apellidos;
			document.getElementById('edad').value = data.edad;
			document.getElementById('genero').value = data.genero;
			document.getElementById('telefono').value = data.telefono;
			document.getElementById('email').value = data.email;
			document.getElementById('contraseña').value = data.contraseña;

			if (data.fotoPerfil) {
				document.getElementById('fotoUsuario').src = data.fotoPerfil;
			} // nos traemos la foto de la base de datos si hay
		})

		.catch(error => {
			console.error('Error al obtener el perfil de usuario:', error);
		});
} //con ésta función traemos los datos de la base de datos

function modificarPerfilUsuario() {

	const perfilActualizado = {
		nombreUsuario: document.getElementById('nombreUsuario').value,
		nombre: document.getElementById('nombre').value,
		apellidos: document.getElementById('apellidos').value,
		edad: document.getElementById('edad').value,
		genero: document.getElementById('genero').value,
		telefono: document.getElementById('telefono').value,
		email: document.getElementById('email').value,
		contraseña: document.getElementById('contraseña').value
	};// ésta función permite modificar los campos y enviarlos a la base de datos

	fetch('/api/modificarPerfilUsuario', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(perfilActualizado)
	})
		.then(response => {
			if (response.ok) {
				alert('Perfil actualizado correctamente');
			} else {
				alert('Error al actualizar el perfil');
			}
		})
		.catch(error => {
			console.error('Error al modificar el perfil de usuario:', error);
		});
}

// ésta función permite eliminar el perfil del usuario
function eliminarPerfilUsuario() {
	const confirmacion = confirm('¿Estás seguro de que deseas eliminar tu perfil? Esta acción no se puede deshacer.');

	if (confirmacion) {
		fetch('/api/eliminarPerfilUsuario', {
			method: 'DELETE'
		})
			.then(response => {
				if (response.ok) {
					alert('Perfil eliminado correctamente');
					// Redirige al usuario a la página de registro
					window.location.href = '/registro.html';
				} else {
					alert('Error al eliminar el perfil');
				}
			})
			.catch(error => {
				console.error('Error al eliminar el perfil de usuario:', error);
			});
	}
}

// Función para cambiar la foto de perfil
function cambiarFoto(event) {
	const archivo = event.target.files[0];
	if (archivo) {
		const reader = new FileReader();
		reader.onload = function(e) {
			document.getElementById('fotoUsuario').src = e.target.result;
		};
		reader.readAsDataURL(archivo);
	}
}

// Llamamos a la función para obtener el perfil del usuario cuando se cargue la página
window.onload = function() {
	obtenerPerfilUsuario();
};
