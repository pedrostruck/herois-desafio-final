moduleApp.controller("jogadorCtrl", jogadorCtrl);

jogadorCtrl.$Inject = ["$scope", "$location", "jogadorService"];

function jogadorCtrl($scope, $location, jogadorService) {
    const self = this;

    self.jogador = {
        nickname: null,
        senha: null,
        heroi: {}
    };

    self.login = function (jogador) {
        jogador.senha = btoa(jogador.senha);
        jogadorService.login(jogador)
            .then(function (response) {
                jogadorService.jogador = response.data;
                alert("Logado com Sucesso");
                $location.path("/batalha");
            }, function(error){
                alert(error.data);
            });
    };

    self.cadastrar = function(jogador) {
        jogador.senha = btoa(jogador.senha);
        jogadorService.inserirJogador(jogador)
        .then(function(response) {
            alert("Cadastrado com Sucesso");
            $location.path("/login");
        }, function(error) {
           alert(error.data.mensagem);
        });
    };

    self.irTela = function (path) {
        $location.path(path);
    };
};