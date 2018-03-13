## Forventet adferd
<!--- If you're describing a bug, tell us what should happen -->
<!--- If you're suggesting a change/improvement, tell us how it should work -->

<!--- Her vil det være narturlig å legge til en form for "HOW TO DEMO" som beskriver hva som skal kunne gjøres for å lukke issuet -->

Formålet med issuet er å kunne opprette en web-server som kjører lokalt på pcen. Serveren skal kunne respondere på HTTP meldinger over en web-browser. I tillegg skal web-serveren integreres med prosjektet slik at det bygger riktig når vi kjører det i git CI.


## Avhengiheter
<!-- Hvilke issues er avhengig av at denne issuen blir ferdig, og hvilke issues er denne issuen avhening av-->
Avhengigheter: (ingen)
Issues som må vente på denne issuen: #63

## Nåværnde oppførsel
<!--- If describing a bug, tell us what happens instead of the expected behavior -->
<!--- If suggesting a change/improvement, explain the difference from current behavior -->

<!--- Har ikke lagt til noe på nåværende oppførsel på denne issuen -->

## Mulig løsning
<!--- Not obligatory, but suggest a fix/reason for the bug, -->
<!--- or ideas how to implement the addition or change -->
Kommer til å bruke denne videoen så langt det fungerer: https://www.youtube.com/watch?v=Vvnliarkw48. @carlfly har sett litt på dette tidligere, så @sverress og @carlfly kommer til å jobbe med dette ganske parallelt.

## Handlingsplan
<!--- Provide a link to a live example, or an unambiguous set of steps to -->
<!--- reproduce this bug. Include code to reproduce, if relevant -->
1. Opprette en ny branch slik at ikke prosjektet påvirkes ved byggefeil ect
2. Lage ny modul med nødvendige tillegg i pom.xml
3. Opprette en Servlet-klasse

## Context
<!--- How has this issue affected you? What are you trying to accomplish? -->
<!--- Providing context helps us come up with a solution that is most useful in the real world -->

<!--- Her burde man også referere til brukerhistorien til denne issuen hvis det eksisterer -->
Brukerhistorie: Nettside for student
Denne webserveren skal videre fungere som et REST API for applikasjonen
