#language: pt

@LojaVirtual
Funcionalidade: Realizar cadastro e compra com sucesso

  @ct01
  Esquema do Cenario: Realizar cadastro de cliente
    Dado eu preencher todos os dados do formulario "<email>","<titulo>","<primeiroNome>","<ultimoNome>","<senha>","<companhia>","<endereco>","<cidade>","<cep>","<estado>","<pais>","<telefone>","<celular>"
    Entao o cadastro do novo cliente e criado

    Exemplos:
      | email                  | titulo | primeiroNome | ultimoNome | senha     | companhia | endereco             | cidade | cep   | estado | pais          | telefone   | celular     |
      | teste.email.1@mail.com | mr     | Chapolin     | Colorado   | abc@12345 | Acme Ltd  | Rua Antonio Agu, 123 | Osasco | 10075 | SP     | United States | 1145671234 | 11999999999 |

# @ct02
#  Esquema do Cenario: Realizar uma compra com sucesso
#    Dado que efetuei a autenticacao de usuario com "<email>" e "<senha>"
#    Quando escolhar um produto e concluir a compra
#    Entao a compra e finalizada com sucesso
#
#    Exemplos:
#      | email                  | senha     |
#      | teste.email.1@mail.com | abc@12345 |
#
  @ct03
  Esquema do Cenario: Não autenticar com email incorreto
    Dado que efetuei a autenticacao de usuario com "<email>" incorreto e "<senha>" valida
    Entao uma mensagem de erro e exibida "<erro>"

    Exemplos:
      | email                  | senha     | erro                                 |
      | email.user@apple.com   | abc@12345 | Your email or password is incorrect! |

  @ct04
  Esquema do Cenario: Não autenticar com senha invalida
    Dado que efetuei a autenticacao de usuario com "<email>" valida e "<senha>" invalida
    Entao uma mensagem de erro e exibida "<erro>"

    Exemplos:
      | email                  | senha     | erro                                 |
      | seu.madruga@gmail.com | S3NH@     | Your email or password is incorrect! |
      | seu.madruga@gmail.com | 123       | Your email or password is incorrect! |
