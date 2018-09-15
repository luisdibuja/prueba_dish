'use strict';

angular.module('pruebaDish').factory('ServicioUsuario',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                cargarTodosLosUsuarios: cargarTodosLosUsuarios,
                obtenerTodosLosUsuarios: obtenerTodosLosUsuarios,
                obtenerUsuario: obtenerUsuario,
                crearUsuario: crearUsuario,
                actualizarUsuario: actualizarUsuario,
                borrarUsuario: borrarUsuario
            };

            return factory;

            function cargarTodosLosUsuarios() {
                console.log('Buscando todos los usuarios');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (respuesta) {
                            console.log('Encontrò exitosamente todos los usuarios');
                            $localStorage.usuarios = respuesta.data;
                            deferred.resolve(respuesta);
                        },
                        function (respuestaError) {
                            console.error('Error al cargar los usuarios');
                            deferred.reject(respuestaError);
                        }
                    );
                return deferred.promise;
            }

            function obtenerTodosLosUsuarios(){
                return $localStorage.usuarios;
            }

            function obtenerUsuario(id) {
                console.log('Buscando Usuario con id :'+id);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + id)
                    .then(
                        function (respuesta) {
                            console.log('Encontrado exitosamente Usuario con id :'+id);
                            deferred.resolve(respuesta.data);
                        },
                        function (errResponse) {
                            console.error('Error al cargar usuario con id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function crearUsuario(usuario) {
                console.log('Creando Usuario');
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API, usuario)
                    .then(
                        function (respuesta) {
                            cargarTodosLosUsuarios();
                            deferred.resolve(respuesta.data);
                        },
                        function (respuestaError) {
                           console.error('Error al crear Usuario : '+respuestaError.data.mensajeDeError);
                           deferred.reject(respuestaError);
                        }
                    );
                return deferred.promise;
            }

            function actualizarUsuario(usuario, id) {
                console.log('Actualizando Usuario con id '+ id);
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + id, usuario)
                    .then(
                        function (respuesta) {
                            cargarTodosLosUsuarios();
                            deferred.resolve(respuesta.data);
                        },
                        function (respuestaError) {
                            console.error('Error al actualizar Usuario con id :'+ id);
                            deferred.reject(respuestaError);
                        }
                    );
                return deferred.promise;
            }

            function borrarUsuario(id) {
                console.log('Borrando Usuario con id '+id);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + id)
                    .then(
                        function (respuesta) {
                            cargarTodosLosUsuarios();
                            deferred.resolve(respuesta.data);
                        },
                        function (respuestaError) {
                            console.error('Error al borrar Usuario con id :'+id);
                            deferred.reject(respuestaError);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);