# Play Consult Github

Este é uma aplicação WEB que retorna uma lista das contribuições em
todos os repositórios GitHub de uma organização consultada.


### Running

Você precisa baixar e instalar o sbt para que este aplicativo seja executado.

Depois de instalar o sbt, o seguinte no prompt de comando iniciará o Play no modo de desenvolvimento:

```bash
sbt run
```

O Play será iniciado na porta HTTP em http://localhost:9000/. Você não precisa implantar ou recarregar nada - alterar qualquer código-fonte enquanto o servidor está em execução recompilará e recarregará automaticamente o aplicativo na próxima solicitação HTTP.

### Lista de Contribuintes

GET  /api/v1/organizations/{orgName}/contributors

#### Parâmetros: 

Nome   | Tipo | Descrição
-------|------|---------
orgName|String| Username de uma organização cadastrada no github

#### Response

```
Status 200
```

```
[
    {
        "login": "joaoexemplo",
        "contributions": 8
    },
    {
        "login": "mariaexemplo",
        "contributions": 3
    },
    {
        "login": "joseexemplo",
        "contributions": 19
    }
]
```