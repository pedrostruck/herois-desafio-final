moduleApp.config(Config);

Config.$inject = ["$routeProvider"];

function Config($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "app/home/home.html"
        })
        .when("/cadastro", {
            templateUrl: "app/cadastro/cadastro.html"
        })
        .when("/home", {
            templateUrl: "app/home/home.html"
        })
        .otherwise({
            redirectTo: "/"
        });
}