var app = angular.module('pruebaDish',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/pruebadish',
    USER_SERVICE_API : 'http://localhost:8080/pruebadish/api/usuario/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'parciales/list',
                controller:'ControladorUsuario',
                controllerAs:'ctrl',
                resolve: {
                    usuarios: function ($q, ServicioUsuario) {
                        console.log('carga todos los usuarios');
                        var deferred = $q.defer();
                        ServicioUsuario.cargarTodosLosUsuarios().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

