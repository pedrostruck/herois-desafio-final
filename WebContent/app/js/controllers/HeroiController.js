moduleApp.controller("HeroiController", HeroiController);

HeroiController.$Inject = ["$scope", "$location", "heroisService"];


function HeroiController($scope, heroisService, $location) {
	const vm = this;
	vm.app = "Batalha de Heróis";
	vm.service = heroisService;
	vm.herois = [];

	vm.init = function() {
		vm.login = true;
		vm.carregarHerois();
	}

	vm.carregarHerois = function () {
		vm.service.getHerois().success(function (data) {
			vm.herois = data;
		}).error(function (data, status) {
			vm.message = "Aconteceu um problema: " + data;
		});
	};

	vm.irCadastrar = function (heroiCadastrado) {
		console.log(heroiCadastrado);
		vm.cadastrar = true;
		vm.login = false;
	}

	vm.irLogin = function (path) {
		$location.path(path)
	}

}