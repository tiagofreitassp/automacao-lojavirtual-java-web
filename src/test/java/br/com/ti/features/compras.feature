#language: pt

@LojaVirtual
Funcionalidade: Realizar cadastro e compra com sucesso

  @ct01
  Esquema do Cenario: Realizar cadastro de cliente
    Dado eu preencher todos os dados do formulario "<email>","<titulo>","<primeiroNome>","<ultimoNome>","<senha>","<companhia>","<endereco>","<cidade>","<cep>","<telefone>","<celular>","<email2>"
    Entao o cadastro do novo cliente e criado

    Exemplos:
      | email                  | titulo | primeiroNome | ultimoNome | senha     | companhia | endereco             | cidade | cep   | telefone   | celular     | email2               |
      | teste.email.7@mail.com | mr     | Chapolin     | Colorado   | abc@12345 | Acme Ltd  | Rua Antonio Agu, 123 | Osasco | 10075 | 1145671234 | 11999999999 | teste.alias@mail.com |


 @ct02
  Esquema do Cenario: Realizar uma compra com sucesso
    Dado que efetuei a autenticacao de usuario com "<email>" e "<senha>"
    Quando escolhar um produto e concluir a compra
    Entao a compra e finalizada com sucesso

    Exemplos:
      | email                  | senha     |
      | teste.email.4@mail.com | abc@12345 |

  @ct03
  Esquema do Cenario: Não autenticar com email incorreto
    Dado que efetuei a autenticacao de usuario com "<email>" incorreto e "<senha>" valida
    Entao uma mensagem de erro e exibida "<erro1>" e "<erro2>"

    Exemplos:
      | email                 | senha     | erro1            | erro2                  |
      | email.user@@apple.com | abc@12345 | There is 1 error | Invalid email address. |

  @ct04
  Esquema do Cenario: Não autenticar com senha invalida
    Dado que efetuei a autenticacao de usuario com "<email>" valida e "<senha>" invalida
    Entao uma mensagem de erro e exibida "<erro1>" e "<erro2>"

    Exemplos:
      | email                  | senha     | erro1            | erro2                  |
      | teste.email.4@mail.com | S3NH@     | There is 1 error | Authentication failed. |
      | teste.email.4@mail.com | 123       | There is 1 error | Invalid password.      |
