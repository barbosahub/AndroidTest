# Detalhes

**Observações**

Foi escolhido o padrão **MVI** com **Jetpack Compose** para o desafio.

- Utilizado padrão **DTO**, **mappers** e **Koin**.
- Separação da camada de **core** (componentização, navegação e outras funcionalidades utilizadas em todo o app).
- Separação da camada de **feature** (features específicas com seus componentes individuais).
- Cada feature segue o padrão **data**, **domain** e **presentation**.


Alguns pontos que percebi e acabei deixando de lado, pois o foco principal foi implementar a arquitetura, o que fez com que o cuidado com o layout ficasse um pouco em segundo plano:

- Algumas coisas não deu para seguir o exemplo do json, por exemplo a tela de dados antiga que não exibe "Privacidade" porque não tem link.
- Modal de sair foi mokado, já que na primeira tela não tem um json para preencher.
- Tela de plano não tinha alguns parametros e titulos, dessa forma coloquei algumas strings para tentar seguir mais ou menos a ideia da NP.
- Criei o projeto separando seus componentes para escalar de forma saudavel, porém não foquei em tipografia, tamanhos, espacamentos, paletas de cores, temas, ícones e coisas referente ao layout.



**Tela Principal**
<table>
<tr>
<td><img src="https://raw.githubusercontent.com/barbosahub/AndroidTest/develop/print/Screenshot_20260303_194751.png" alt="Tela 1" width="300"/></td>
<td><img src="https://raw.githubusercontent.com/barbosahub/AndroidTest/develop/print/Screenshot_20260303_194807.png" alt="Tela 2" width="300"/></td>
</tr>
</table>


 **Tela Meus dados**

<table>
<tr>
<td><img src="https://raw.githubusercontent.com/barbosahub/AndroidTest/develop/print/Screenshot_20260303_194813.png" alt="Tela 3" width="300"/></td>
<td><img src="https://github.com/barbosahub/AndroidTest/blob/develop/print/Screenshot_20260303_194920.png" alt="Tela 3" width="300"/></td>
</tr>

<tr>
<td><img src="https://raw.githubusercontent.com/barbosahub/AndroidTest/develop/print/Screenshot_20260303_194836.png" alt="Tela 4" width="300"/></td>
<td><img src="https://github.com/barbosahub/AndroidTest/blob/develop/print/Screenshot_20260303_194931.png" alt="Tela 4" width="300"/></td>
</tr>



<tr>
<td><img src="https://github.com/barbosahub/AndroidTest/blob/develop/print/Screenshot_20260303_194856.png" alt="Tela 4" width="300"/></td>
<td><img src="https://github.com/barbosahub/AndroidTest/blob/develop/print/Screenshot_20260303_194906.png" alt="Tela 4" width="300"/></td>
<td><img src="https://github.com/barbosahub/AndroidTest/blob/develop/print/Screenshot_20260303_194912.png" alt="Tela 4" width="300"/></td>
</tr>

</table>




 **Tela Meu Plano**

<table>
<tr>
<td><img src="https://github.com/barbosahub/AndroidTest/blob/develop/print/Screenshot_20260303_194843.png" alt="Tela 3" width="300"/></td>
<td><img src="https://github.com/barbosahub/AndroidTest/blob/develop/print/Screenshot_20260303_194937.png" alt="Tela 3" width="300"/></td>
</tr>


</table>





