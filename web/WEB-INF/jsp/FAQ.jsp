<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Contatti"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>FAQ</h2>
        </div>
        <div class="card FAQ" id="buttonCard">
            <h3> Scegli tra le seguenti categorie quella che più ti interessa</h3>
            <div id="buttondiv">
                <a href="#ordini"> Ordini</a>
                <a href="#pagamenti">Pagamenti</a>
                <a href="#spedizioni">Spedizioni</a>
                <a href="#registrazione"> Registrazione</a>
            </div>
        </div>
        <div class="card FAQ" id="maggioriDomande">
            <h3> Di seguito sono riportate le domande che più interessano ai nostri clienti</h3>
            <div class="toggle">
                <button id="md1" onclick="functionToggle(this.id)">QUANDO SARÀ EFFETTUATO L’ADDEBITO SULLA MIA CARTA DI CREDITO O SUL MIO CONTO PAYPAL?</button>
                <p>L'importo relativo all'acquisto effettuato verrà addebitato sulla tua carta di credito al momento della spedizione dell'ordine. <br>
                    ATTENZIONE: quello che viene visualizzato al momento dell’inserimento dell’ordine non è un prelevamento di fondi ma solo
                    l’autorizzazione che viene richiesta, mentre l’importo viene effettivamente addebitato soltanto nel momento della spedizione
                    della merce. In caso di spedizione parziale dell’ordine, ti verrà addebitato solo il valore della merce spedita.</p>
            </div>
            <div class="toggle">
                <button id="md2" onclick="functionToggle(this.id)">VERRÒ AVVERTITO QUANDO PARTE LA SPEDIZIONE?</button>
                <p>Sì, riceverai una mail sia nel momento in cui il tuo ordine sarà confermato (contenente tutti i dati dei prodotti e dell’indirizzo
                    di spedizione) che quando il tuo ordine verrà affidato al corriere incaricato della consegna (contenente il link relativo allo stato
                    della spedizione, che potrai controllare dal giorno successivo alla ricezione della mail). </p>
            </div>
            <div class="toggle">
                <button id="md3" onclick="functionToggle(this.id)">HO CAMBIATO IDEA SU UN PRODOTTO (DIRITTO DI RECESSO). POSSO RESTITUIRLO?</button>
                <p>Ai sensi e per gli effetti dell'art. 54 del Codice del Consumo, entro il termine di quattordici giorni dalla consegna del prodotto potrai
                    esercitare il Diritto di Recesso, ottenendo il rimborso integrale degli importi corrisposti per l'acquisto del prodotto ivi inclusi i costi di consegna sostenuti.<br>
                    Nel caso in cui tu abbia scelto di farti consegnare con più spedizioni i prodotti richiesti in un singolo ordine o in caso di prodotto costituito da lotti o pezzi
                    multipli da consegnarsi con più spedizioni, il termine per esercitare il recesso decorre dalla consegna dell'ultimo prodotto. Le spese di restituzione saranno a tuo carico. </p>
            </div>
            <div class="toggle">
                <button id="md4" onclick="functionToggle(this.id)">IL MIO ORDINE È ARRIVATO DANNEGGIATO O IL PRODOTTO È DIFETTOSO. COSA POSSO FARE?</button>
                <p>Scrivici indicando il numero d’ordine e il codice del prodotto danneggiato o difettoso e segui le istruzioni che ti darà il Servizio Clienti. </p>
            </div>

            <div class="toggle">
                <button id="md5" onclick="functionToggle(this.id)">COME SARANNO TRATTATI I MIEI DATI?</button>
                <p>BookStore Srl garantisce la massima riservatezza sulle informazioni che hai rilasciato al momento della registrazione: sono infatti
                    inviate in una connessione protetta con tecnologia SSL. <br> La sicurezza del sito è garantita e certificata da Verisign società leader
                    mondiale tra i fornitori di servizi per la sicurezza online. Le informazioni facoltative che richiediamo all'atto della registrazione sono
                    utilizzate per migliorare il servizio e offrirti promozioni basate sui tuoi interessi.
                </p>
            </div>
        </div>
        <div class="card FAQ" id="ordini">
            <h3>ORDINI</h3>
            <div class="toggle">
                <button id="o1" onclick="functionToggle(this.id)">COME POSSO EFFETTUARE UN ORDINE?</button>
                <p>Cerca i prodotti che desideri, scegli quelli che fanno per te e aggiungili al carrello.<br> Seleziona le modalità di consegna e pagamento
                    che preferisci, compila con i tuoi dati (necessari per spedirti i prodotti desiderati) e conferma l'ordine. Riceverai una email di conferma
                    contenente con i dati del tuo ordine.Cerca i prodotti che desideri, scegli quelli che fanno per te e aggiungili al carrello. Seleziona le
                    modalità di consegna e pagamento che preferisci, compila con i tuoi dati (necessari per spedirti i prodotti desiderati) e conferma l'ordine.
                    Riceverai una email di conferma contenente con i dati del tuo ordine.</p>
            </div>
            <div class="toggle">
                <button id="o2" onclick="functionToggle(this.id)">IN QUANTO TEMPO RICEVERÒ IL MIO ORDINE?</button>
                <p>I nostri ordini vengono spediti entro un giorno lavoratvo (consultabile tramite il countdown presente nella pagina del prodotto) a seguito dell'acquisto.
                Normalmente i tempi di ricezione vanno dlle 24 alle 48 grazie alla spedizione tramite corriere espresso.</p>
            </div>
            <div class="toggle">
                <button id="o3" onclick="functionToggle(this.id)">COME POSSO ACCEDERE ALLA LISTA DEI MIEI ORDINI?</button>
                <p>Accedi al tuo account e clicca su "i miei ordini": qui potrai vedere i dati relativi a tutti gli ordini da te effettuati in passato e a quelli in corso.
                    Se vuoi invece controllare lo stato di un singolo ordine, clicca su “vai al dettaglio”.</p>
            </div>
            <div class="toggle">
                <button id="o4" onclick="functionToggle(this.id)">COSA ACCADE SE UN PRODOTTO DEL MIO ORDINE NON È PIÙ DISPONIBILE?</button>
                <p>Se in fase di evasione di un ordine un prodotto non fosse più disponibile (perché in ristampa, esaurito o non reperibile presso il fornitore), una mail
                    ti informerà dell’inconveniente e il tuo ordine verrà aggiornato con l’eliminazione dell’articolo non disponibile. Gli altri prodotti verranno spediti e
                    l’importo dell’articolo non disponibile verrà automaticamente detratto dal totale di spesa indicato in fase di acquisto, indipendentemente dal metodo di pagamento prescelto.</p>
            </div>
            <div class="toggle">
                <button id="o5" onclick="functionToggle(this.id)">EFFETTUATE CONSEGNE ALL'ESTERO?</button>
                <p>No, per ora ci limitiamo a spedire ordini solamente in Italia.</p>
            </div>
            <div class="toggle">
                <button id="o6" onclick="functionToggle(this.id)">SE HO BISOGNO DI GRANDI QUANTITATIVI. COSA POSSO FARE?</button>
                <p>Scrivici nella sezione contatti indicando i prodotti e le quantità desiderate, ti risponderemo il prima possibile mandandoti un preventivo personalizzato.</p>
            </div>
            <a href="#buttonCard" class="up">⇧</a>
        </div>

        <div class="card FAQ" id="pagamenti">
            <h3>PAGAMENTI</h3>
            <div class="toggle">
                <button id="p1" onclick="functionToggle(this.id)">QUALI METODI DI PAGAMENTO ACCETTATE?</button>
                <p>Accettiamo carte di credito (Visa, Mastercard, American Express, Diners) e pagamento in contanti alla consegna tramite servizio di Contrassegno (solo per l’Italia).
                    Questa modalità prevede un costo aggiuntivo per il “Diritto di Contrassegno(la valuta accettata è l'euro).</p>
            </div>
            <div class="toggle">
                <button id="p2" onclick="functionToggle(this.id)">PERCHÈ LA MIA CARTA DI CREDITO NON È STATA ACCETTATA?</button>
                <p>I principali motivi possono essere:<br>
                    • Hai superato il limite di credito della tua carta;<br>
                    • I dati richiesti per il pagamento non coincidono con quelli tua carta. Un semplice errore di battitura può causare il rifiuto dell'operazione;<br>
                    • La tua carta è scaduta: controlla la data di scadenza, che trovi sulla carta stessa;<br>
                    Per ogni ulteriore informazione, la tua banca può darti tutte le informazioni necessarie sulla tua carta e sulle possibilità di pagamento.</p>
            </div>
            <div class="toggle">
                <button id="p3" onclick="functionToggle(this.id)">QUANDO SARÀ EFFETTUATO L’ADDEBITO SULLA MIA CARTA DI CREDITO?</button>
                <p>L'importo relativo all'acquisto effettuato verrà addebitato sulla tua carta di credito al momento della spedizione dell'ordine.<br>
                    ATTENZIONE: quello che viene visualizzato al momento dell’inserimento dell’ordine non è un prelevamento di fondi ma solo l’autorizzazione
                    che viene richiesta, mentre l’importo viene effettivamente addebitato soltanto nel momento della spedizione della merce.</p>
            </div>
            <div class="toggle">
                <button id="p4" onclick="functionToggle(this.id)">I MIEI PAGAMENTI SONO SICURI?</button>
                <p>Certamente: sul nostro Portale i pagamenti sono e resteranno protetti mediante un avanzato sistema di difesa dei dati in rete.
                    Il pagamento con carta di credito avviene su un server sicuro gestito da società terza, specializzata e debitamente autorizzata
                    ai sensi delle leggi vigenti a svolgere tale servizio: pertanto, i dati relativi alla carta di credito vengono processati esclusivamente
                    dal Gestore Pagamenti. Mondadori Store, quindi non avrà accesso ai dati relativi alle carte di credito, ma gestirà solo i Dati dell'Utente.
                    Nessuna responsabilità potrà essere attribuita a Mondadori Store per qualsiasi utilizzo fraudolento da parte di terzi dei dati relativi alla
                    carta di credito dell'Utente: in tale evenienza l'Utente dovrà contattare immediatamente il Gestore Pagamenti ed eventualmente le Autorità competenti.</p>
            </div>
            <a href="#buttonCard" class="up">⇧</a>
        </div>
        <div class="card FAQ" id="spedizioni">
            <h3>SPEDIZIONI</h3>
            <div class="toggle">
                <button id="s1" onclick="functionToggle(this.id)">QUANTO COSTA LA SPEDIZIONE?</button>
                <p>Il costo delle nostre spedizioni varia dipendentemente dal costo dell'ordine totale, ovvero:<br>
                    • 15€ per qualsiasi ordine il cui costo totale è inferiore a 50€<br>
                    • 7,50€ per qualsiasi ordine il cui costo totale è inferiore a 100€<br>
                    • gratuita per qualsiasi ordine il cui costo totale è superiore a 100€
                </p>
            </div>
            <div class="toggle">
                <button id="s2" onclick="functionToggle(this.id)">COME AVVIENE LA SPEDIZIONE?</button>
                <p>Una volta preparato il pacco con tutti i prodotti che hai acquistato, provvederemo a contattare il corriere e spedirlo verso l'indirizzo indicato.</p>
            </div>
            <div class="toggle">
                <button id="s3" onclick="functionToggle(this.id)">COSA SUCCEDE SE NON C'È NESSUNO QUANDO ARRIVA IL CORRIERE?</button>
                <p>Ti verrà lasciato un modulo che contiene tutti i riferimenti del corriere per poter definire una riconsegna.</p>
            </div>
            <div class="toggle">
                <button id="s4" onclick="functionToggle(this.id)">VERRÒ AVVERTITO QUANDO PARTE LA SPEDIZIONE?</button>
                <p>Sì, riceverai una mail sia nel momento in cui il tuo ordine sarà confermato (contenente tutti i dati dei prodotti e dell’indirizzo di spedizione)
                    che quando il tuo ordine verrà affidato al corriere incaricato della consegna (contenente il link relativo allo stato della spedizione, che potrai
                    controllare dal giorno successivo alla ricezione della mail).</p>
            </div>
            <a href="#buttonCard" class="up">⇧</a>
        </div>
        <div class="card FAQ" id="registrazione">
            <h3>REGISTRAZIONE</h3>
            <div class="toggle">
                <button id="r1" onclick="functionToggle(this.id)">A COSA SERVE ISCRIVERSI?</button>
                <p>L'iscrizione ti consente di essere riconosciuto dal sistema e di accedere alla tua Area personale del sito, dalla quale potrai:<br>
                    • Visualizzare lo storico degli ordini inviati.<br>
                    • Modificare gli ordini in corso di lavorazione.<br>
                    • Modificare i tuoi dati di registrazione.<br>
                    • Acquistare libri.<br>
                    • Mantenere elementi nel proprio carrello.<br>
                </p> <p>
                    Iscrivendoti, potrai inoltre:<br>
                • Creare una Collezione: una lista dei desideri/preferiti, dove raccogliere i titoli che vuoi tenere sotto osservazione per futuri acquisti.<br>
                • Ordinare più velocemente, memorizzando gli indirizzi di spedizione.<br>
                In ogni caso è possibile visitare ed utilizzare la maggior parte delle funzionalità del nostro sito anche senza registrarsi.
                </p>
            </div>
            <div class="toggle">
                <button id="r2" onclick="functionToggle(this.id)">COME POSSO ISCRIVERMI?</button>
                <p>Puoi iscriverti cliccando "Accedi o Registrati" in alto a destra in home page. Ti sarà chiesto di scegliere un nome utente, una password e di inserire un indirizzo e-mail.
                    È importante inserire un indirizzo e-mail valido perché servirà per notificarti la conferma dell’ordine, i tempi di prevista disponibilità, la spedizione della merce e
                    altre informazioni utili sul tuo ordine.</p>
            </div>
            <div class="toggle">
                <button id="r3" onclick="functionToggle(this.id)">L’ISCRIZIONE È GRATUITA?</button>
                <p>Sì : non ti sarà addebitato alcun costo per esserti registrato.</p>
            </div>
            <div class="toggle">
                <button id="r4" onclick="functionToggle(this.id)">DEVO COMUNICARVI LA MIA EMAIL: RICEVERÒ POSTA INDESIDERATA?</button>
                <p>No! Riceverai soltanto e-mail riguardanti la gestione dei tuoi ordini.<br>
                    Se hai acconsentito al trattamento dei dati per finalità promozionali, riceverai la nostra newsletter con le migliori promozioni.
                    In qualsiasi momento puoi cancellare l'iscrizione, eliminando l'autorizzazione dalla tua Area personale.
                </p>
            </div>
            <div class="toggle">
                <button id="r5" onclick="functionToggle(this.id)">I MIEI DATI SARANNO COMUNICATI AD ALTRE PERSONE?</button>
                <p>No! I tuoi dati saranno utilizzati soltanto da la Feltrinelli e i suoi partner (quali corrieri espressi e banche) per la gestione degli ordini.
                    In nessun caso saranno ceduti ad aziende terze ed estranee al tuo ordine.
                </p>
            </div>
            <div class="toggle">
                <button id="r6" onclick="functionToggle(this.id)">COME POSSO MODIFICARE I MIEI DATI DI REGISTRAZIONE?</button>
                <p>Puoi modificare i dati di registrazione accedendo alla tua Area personale, alla sezione "I tuoi dati".</p>
            </div>
            <a href="#buttonCard" class="up">⇧</a>
        </div>
    </div>

    <script>
        //funzione per attivazione dei toggle dei bottoni
            function functionToggle(id) {
                console.log(id);
                $("#"+id).siblings().toggle(300, "linear");
            }
        //funzione per lo scorrimento animato della pagina
        $(document).ready(function(){
            $("a").on('click', function(event) {
                if (this.hash !== "") {
                    event.preventDefault();
                    var hash = this.hash;
                    $('html, body').animate({
                        scrollTop: $(hash).offset().top
                    }, 300, function(){
                        window.location.hash = hash;
                    });
                }
            });
        });
    </script>
    <jsp:include page="footererightcollum.jsp"/>