const urlBaseServicio = 'http://localhost:8080';
const urlServicioUsuario = urlBaseServicio+'/usuario/';

const errorDiv = document.getElementById('error-alert');
const tablaDiv = document.getElementById('body-tabla-usuarios')
const form = document.getElementById('usuario-form');

form.addEventListener('submit', async function(event) {
    event.preventDefault();
    const formData = new FormData(form);
    const datos = {
        id:formData.get('id'),
        nombre: formData.get('nombre'),
        email: formData.get('email'),
        telefono: formData.get('telefono'),
        password:formData.get('contrasena')
    };
    try {
        await guardar(datos);
    } catch (error) {
        mostrarError(error);
    }
    alert('Guardado!');
    resetForm();
    cargar();
});

async function main(){
    cargar();
}

function resetForm(){
    form.reset();
}

async function cargar(){
    try {
        let usuarios = await obtenerUsuarios();
        actualizarUsuarios(usuarios);
    } catch (error) {
        mostrarError(error);
    }
}

function actualizarUsuarios(usuarios){
    tablaDiv.innerHTML = '';
    for (let i = 0; i < usuarios.length; i++) {
        const u = usuarios[i];

        const row = document.createElement('tr');
        const idEl = document.createElement('td');
        const nombreEl = document.createElement('td');
        const emailEl = document.createElement('td');
        const telefonoEl = document.createElement('td');
        const actionEl = document.createElement('td')

        idEl.innerText = u.id;
        nombreEl.innerText = u.nombre;
        emailEl.innerText = u.email;
        telefonoEl.innerText = u.telefono;

        const botonEliminar = document.createElement('button');
        botonEliminar.innerText='Eliminar';

        botonEliminar.addEventListener('click',()=>{
            eliminar(u.id);
        });

        const botonEditar = document.createElement('button');
        botonEditar.innerText='Editar';

        botonEditar.addEventListener('click',()=>{
            form.elements['id'].value = u.id;
            form.elements['nombre'].value = u.nombre;
            form.elements['email'].value = u.email;
            form.elements['telefono'].value = u.telefono;
            form.elements['contrasena'].value = '';
        });

        actionEl.appendChild(botonEditar);
        actionEl.appendChild(botonEliminar);

        row.appendChild(idEl);
        row.appendChild(nombreEl);
        row.appendChild(emailEl);
        row.appendChild(telefonoEl);
        row.appendChild(actionEl);


        tablaDiv.appendChild(row);
    }
}

function mostrarError(error){
    errorDiv.innerText = error;
    errorDiv.style.display = 'block';
}


async function eliminar(id){
    try {
        const solicitud = await fetch(urlServicioUsuario + id, {
            method: 'DELETE'
        });
        if (!solicitud.ok) {
            throw new Error('Error al eliminar usuario');
        }
        alert('eliminado!');
        cargar();
    } catch (error) {
        mostrarError(error);
    }
}

/**
 * 
 * @returns {id:number,nombre:string,email:string,telefono:string}[]
 */
async function obtenerUsuarios(){
    const solicitud = await fetch(urlServicioUsuario);
    const usuarios = await solicitud.json();
    return usuarios;
}

async function guardar(userData){
    let solicitud;
    let method = 'POST';
    if(userData.id){
        userData.id = parseInt(userData.id);
        method = 'PUT';
    }
    solicitud = await fetch(urlServicioUsuario, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    });
    if (!solicitud.ok){
        throw new Error('Error al guadar.')
    }
}






main();