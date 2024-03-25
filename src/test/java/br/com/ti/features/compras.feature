#language: pt

@LojaVirtual
Funcionalidade: Realizar cadastro e compra com sucesso

  @ct01
  Esquema do Cenario: Realizar cadastro de cliente
    Dado eu preencher todos os dados do formulario "<email>","<titulo>","<primeiroNome>","<ultimoNome>","<senha>","<companhia>","<endereco>","<cidade>","<cep>","<telefone>","<celular>","<email2>"
    Entao o cadastro do novo cliente e criado

    Exemplos:
      | email                  | titulo | primeiroNome | ultimoNome | senha     | companhia | endereco             | cidade | cep   | telefone   | celular     | email2               |
      | teste.email.6@mail.com | mr     | Chapolin     | Colorado   | abc@12345 | Acme Ltd  | Rua Antonio Agu, 123 | Osasco | 10075 | 1145671234 | 11999999999 | teste.alias@mail.com |


 @ct02
  Esquema do Cenario: Realizar uma compra com sucesso
    Dado que efetuei a autenticacao de usuario com "<email>" e "<senha>"
    Quando escolhar um produto e concluir a compra
    Entao a compra e finalizada com sucesso

    Exemplos:
      | email                  | senha     |
      | teste.email.4@mail.com | abc@12345 |
