const moduleApp = angular.module('heroi', ['ngRoute']);

moduleApp.run(Run);

Run.$inject = ['$rootScope', '$location', 'jogadorService'];

function Run($rootScope, $location, jogadorService) {
    /*$rootScope.$on('$routeChangeStart', function (evt, route) {
        if (route.originalPath !== "/") {
            if (!jogadorService.jogador) {
                $location.path("/");
            }
        } else {
            if (jogadorService.jogador) {
                $location.path("/home");
            }
        }
    });*/
}