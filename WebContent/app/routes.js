moduleApp.config(Config);

Config.$inject = ["$routeProvider"];

function Config($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "app/html/login.html",
            controller: "JogadorController as vm"
        })
        .when("/cadastrar", {
            templateUrl: "app/html/cadastrar.html",
            controller: "HeroiController as vm"
        })
        .when("/batalha",{
            templateUrl: "app/html/batalha.html",
            controller: "BatalhaController as vm"
        })
        .otherwise({
            redirectTo: "/"
        });
}