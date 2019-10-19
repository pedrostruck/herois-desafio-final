moduleApp.config(Config);

Config.$inject = ["$routeProvider"];

function Config($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "app/html/login.html",
            controller: "jogadorCtrl as vm"
        })
        .when("/cadastrar", {
            templateUrl: "app/html/cadastrar.html",
            controller: "heroiCtrl as vm"
        })
        .when("/batalha",{
            templateUrl: "app/html/batalha.html",
            controller: "batalhaCtrl as vm"
        })
        .otherwise({
            redirectTo: "/"
        });
}