moduleApp.controller("BatalhaController", BatalhaController);

BatalhaController.$Inject = ["$scope", "$location", "batalhaService","jogadorService"];


function BatalhaController($scope, $location, batalhaService, jogadorService) {
	const vm = this;

	vm.telaVsJogador = true;

	vm.jogadores = [];

	vm.jogador = jogadorService.jogador;
	
	vm.jogador2 = {
		nickname: null,
		personagem: {}
	};

	vm.vencedor = {
		nickname: null,
		personagem: {}
	};

	vm.carregarJogadoresOponentes = function () {
		vm.service.getHerois().success(function (data) {
			vm.jogadores = data;
		}).error(function (data, status) {
			vm.message = "Aconteceu um problema: " + data;
		});
	};
	
	vm.batalharVsJogador = function (jogador, jogador2) {
		batalhaService.batalharVsJogador(jogador, jogador2).success(function (data) {
			vm.vencedor = data;
		}).error(function (data, status) {
			vm.message = "Aconteceu um problema: " + data;
		});
	};

	vm.batalharVsComputador = function () {
		batalhaService.batalharVsComputador().success(function (data) {
			vm.vencedor = data;
		}).error(function (data, status) {
			vm.message = "Aconteceu um problema: " + data;
		});
	};

	vm.irTelaVsComputador = function () {
		vm.telaVsComputador = true;
		vm.telaVsJogador = false;
		vm.batalhaVsComputador();
	}

	vm.irTelaVsJogador = function () {
		vm.telaVsJogador = true;
		vm.telaVsComputador = false;
	}

}