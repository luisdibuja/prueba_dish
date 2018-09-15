'use strict';

angular.module('pruebaDish').controller('ControladorUsuario',
    ['ServicioUsuario', '$scope',  function(ServicioUsuario , $scope) {

        var self = this;
        self.usuario = {};
        self.usuarios=[];

        self.submit = submit;
        self.obtenerTodosLosUsuarios = obtenerTodosLosUsuarios;
        self.crearUsuario = crearUsuario;
        self.actualizarUsuario = actualizarUsuario;
        self.borrarUsuario = borrarUsuario;
        self.editarUsuario = editarUsuario;
        self.reset = reset;

        self.mensajeExitoso = '';
        self.mensajeDeError = '';
        self.completado = false;

        self.soloEnteros = /^\d+$/;
        self.soloNumeros = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Enviando');
            if (self.usuario.id === undefined || self.usuario.id === null) {
                console.log('Guardando un nuevo usuario', self.usuario);
                crearUsuario(self.usuario);
            } else {
                actualizarUsuario(self.usuario, self.usuario.id);
                console.log('Usuario actualizado con id ', self.usuario.id);
            }
        }

        function crearUsuario(usuario) {
            console.log('A punto de crear usuario');
            ServicioUsuario.crearUsuario(usuario)
                .then(
                    function (respuesta) {
                        console.log('Usuario creado exitosamente');
                        self.mensajeExitoso = 'Usuario creado exitosamente';
                        self.mensajeDeError='';
                        self.completado = true;
                        self.usuario={};
                        $scope.myForm.$setPristine();
                    },
                    function (respuestaError) {
                        console.error('Error mientras se creaba el Usuario');
                        self.mensajeDeError = 'Error mientras se creaba el Usuario: ' + respuestaError.data.mensajeDeError;
                        self.mensajeExitoso='';
                    }
                );
        }


        function actualizarUsuario(usuario, id){
            console.log('A punto de actualizar usuario');
            ServicioUsuario.actualizarUsuario(usuario, id)
                .then(
                    function (respuesta){
                        console.log('Usuario actualizado exitosamente');
                        self.mensajeExitoso='Usuario actualizado exitosamente';
                        self.mensajeDeError='';
                        self.completado = true;
                        $scope.myForm.$setPristine();
                    },
                    function(respuestaError){
                        console.error('Error mientras se actualizaba Usuario');
                        self.mensajeDeError='Error mientras se actualizaba Usuario '+respuestaError.data;
                        self.mensajeExitoso='';
                    }
                );
        }


        function borrarUsuario(id){
            console.log('A punto de borrar usuario con id '+id);
            ServicioUsuario.borrarUsuario(id)
                .then(
                    function(){
                        console.log('usuario '+id + ' borrado exitosamente');
                    },
                    function(respuestaError){
                        console.error('Error mientras se borraba el usuario '+id +', Error :'+respuestaError.data);
                    }
                );
            reset();
        }


        function obtenerTodosLosUsuarios(){
            return ServicioUsuario.obtenerTodosLosUsuarios();
        }

        function editarUsuario(id) {
            self.mensajeExitoso='';
            self.mensajeDeError='';
            ServicioUsuario.obtenerUsuario(id).then(
                function (usuario) {
                    self.usuario = usuario;
                },
                function (respuestaError) {
                    console.error('Error al borrar usuario ' + id + ', Error :' + respuestaError.data);
                }
            );
        }
        function reset(){
            self.mensajeExitoso='';
            self.mensajeDeError='';
            self.usuario={};
            $scope.myForm.$setPristine(); //resetear Forma
        }
    }


    ]);