moduleApp.controller('BatalhaController', BatalhaController);

BatalhaController.$Inject = [ '$scope', '$location', 'batalhaService', 'jogadorService' ];

function BatalhaController($scope, $location, batalhaService, jogadorService) {
	const vm = this;

	vm.telaVsJogador = true;
	vm.batalhaService = batalhaService;
	vm.log = null;
	vm.acabouBatalha = false;
	vm.jogadores = [];
	vm.jogador2 = {
		nickname: null,
		personagem: {}
	};
	vm.vencedor = {
		nickname: null,
		personagem: {}
	};

	vm.init = function() {
		vm.jogador = jogadorService.jogador;
		vm.carregarJogadoresOponentes(vm.jogador);
	};

	vm.carregarJogadoresOponentes = function(jogadorLogado) {
		vm.batalhaService
			.getOponentes(jogadorLogado)
			.success(function(data) {
				vm.jogadores = data;
			})
			.error(function(data, status) {
				vm.message = 'Aconteceu um problema: ' + data;
			});
	};

	vm.batalhar = function(jogadorNick, oponenteNick) {
		batalhaService
			.batalhar(jogadorNick, oponenteNick)
			.success(function(data) {
				vm.acabouBatalha = true;
				vm.log = data;
			})
			.error(function(data, status) {
				vm.message = 'Aconteceu um problema: ' + data;
			});
	};

	vm.irTelaVsComputador = function() {
		vm.telaVsComputador = true;
		vm.log = null;
		vm.telaVsJogador = false;
	};

	vm.irTelaVsJogador = function() {
		vm.telaVsJogador = true;
		vm.telaVsComputador = false;
	};
}
