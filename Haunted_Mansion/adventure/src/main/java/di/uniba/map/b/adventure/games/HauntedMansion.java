/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import di.uniba.map.b.adventure.type.ClosedRoom;
import di.uniba.map.b.adventure.type.Enigma;
import di.uniba.map.b.adventure.type.EnigmaChiuso;
import di.uniba.map.b.adventure.type.EnigmaOggetto;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Iterator;

/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe GameDescription. L'Engine è fatto in modo che possa
 * eseguire qualsiasi gioco che estende GameDescription, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicate: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (nextMove) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author Luca Montinaro
 */

public class HauntedMansion extends GameDescription {
	
	private Enigma lastEnigma;
	
	public ClosedRoom getCurrentRoom(){
	    return (ClosedRoom) super.getCurrentRoom();
	}

    @Override
    public void init() throws Exception {
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getCommands().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommands().add(pickup);
        Command push = new Command(CommandType.USE, "usa");
        push.setAlias(new String[]{"Usa"});
        getCommands().add(push);
        
        //Rooms
        ClosedRoom entrance = new ClosedRoom(0, "Ingresso", "Sei entrato da qui, sarà meglio muoversi a trovare la chiave per uscire");
        entrance.setLook("La porta non si apre");
        ClosedRoom library = new ClosedRoom(1, "Libreria", "Questo posto e' pieno di libri!");
        library.setLook("Dietro una libreria sembra esserci qualcosa! posso prendere questo cacciavite che e' sulla scrivania potrebbe tornare comodo");
        ClosedRoom kitchen = new ClosedRoom(2, "Cucina","Questo sembra il posto ideale per cucinarsi qualcosa, ma non e' questo il momento");
        kitchen.setLook("Sembra non esserci nulla oltre alle solite padelle e posate");
        ClosedRoom dininghall = new ClosedRoom(3, "Sala da Pranzo","Un tavolo con sei sedie, wow famiglia numerosa!");
        dininghall.setLook("Chi viveva qui doveva essere ricco! Ma sotto il tavolo c'è qualcuno!");
        ClosedRoom hall = new ClosedRoom(4, "Salotto","Stanza molto poco arredata, mi aspettavo di piu'");
        hall.setLook("Wow ci sono delle poltrone molto comode ma sembrano impolverate");
        ClosedRoom livingroom = new ClosedRoom(5,"Soggiorno","Si vede che qui la famiglia trascorreva molto tempo!");
        livingroom.setLook("Ahia! Ci sono dei giochi per terra non gli ho visti, e sul muro ci sono anche dei trofei! Forse potrei prendere l'ascia");
        ClosedRoom hallway_1 = new ClosedRoom(6,"Corridoio","Molto stretto come corridoio");
        hallway_1.setLook("Quanti quadri devono essere i vecchi proprietari");
        ClosedRoom hallway_2 = new ClosedRoom(7,"Corridoio","Questo corridoio sembra non finire!");
        hallway_2.setLook("E continuano i quadri!");
        ClosedRoom hallway_3 = new ClosedRoom(8,"Corridoio","Vedo la porta alla fine! ");
        hallway_3.setLook("Troppi quadri comunque!");
        ClosedRoom masterbedroom = new ClosedRoom(9,"Stanza da letto patrimoniale","Wow, il letto e' disfatto che educazione e' questa!");
        masterbedroom.setLook("Il pavimento e' sporco non l'ha pulito nessuno!");
        ClosedRoom childbedroom = new ClosedRoom(10,"Cameretta","Questa deve essere la cameretta dei bambini. ");
        childbedroom.setLook("Devono aver avuto una bella infanzia ci sono molti giochi qui!");
        ClosedRoom garden_2 = new ClosedRoom(11,"Giardino","Qui fuori fa troppo freddo perchè si possa uscire!");
        garden_2.setLook("Nessuno lo cura e si vede!");
        ClosedRoom hallway_7 = new ClosedRoom(12,"Corridoio","Un altro corridoio, uff!");
        hallway_7.setLook("Altri quadri penso che siano i loro antenati!");
        ClosedRoom garden = new ClosedRoom(13,"Giardino","Sembra molto curato!");
        garden.setLook("Ehy c'e' qualcosa che risplende tra i cespugli! Ma e' la chiave ora posso uscire di qui!");
        ClosedRoom hallway_5 = new ClosedRoom(14,"Corridoio","Wow un corridoio che originale!");
        hallway_5.setLook("Wow altri quadri e mobilia varia, chissa' quanto ci mettono a pulire tutto!");
        ClosedRoom hallway_6 = new ClosedRoom(15,"Corridoio","Ancora corridoio yuppie!");
        hallway_6.setLook("C'e' una torcia a terra. A qualcuno sarà caduta!");
        ClosedRoom bathroom = new ClosedRoom(16,"Bagno","Wow doccia e vasca un pò troppo!");
        bathroom.setLook("Sento qualcuno piangere nella doccia!");
        ClosedRoom billiardroom = new ClosedRoom(17,"Sala da biliardo","Wow hanno un biliardo a casa, fico!");
        billiardroom.setLook("Ma la tenda si è mossa?");
        ClosedRoom study = new ClosedRoom(18,"Studio","Ehy qui ci sono molte scrivanie!");
        study.setLook("Ma c'è una maniglia per terra meglio prenderla!"); 
        
        //enigma
        EnigmaChiuso entraceS = new EnigmaChiuso(100,"Rimangono attraenti anche al buio: cosa sono?","a. Calamite","b. Silenzio","c. Poli",'a');
        entrance.setEnigmaS(entraceS);
        hall.setEnigmaN(entraceS);
        EnigmaChiuso entraceE = new EnigmaChiuso(101,"In un'aia vi sono conigli e polli; ma in tutto sono 100 zampe e 40 	ceste. Quanti conigli e quanti polli sono?","a. 4 e 5 ","b. 100 e 40","c. 30 e 10 ",'c');
        entrance.setEnigmaE(entraceE);
        dininghall.setEnigmaW(entraceE);
        EnigmaChiuso entranceN = new EnigmaChiuso(102,"Ho 5 cugini. Ognuno di loro ha una sorella. Quanti bambini ci sono in totale?","a. 10","b. 6","c. 20",'b');
        entrance.setEnigmaN(entranceN);
        library.setEnigmaS(entranceN);
        EnigmaChiuso libraryE = new EnigmaChiuso(103,"Paolo e Andrea hanno in tasca la stessa somma di denaro. Quanti soldi 	deve dare Paolo ad Andrea affinché Andrea abbia esattamente 100 euro più di Paolo?","a. 50€","b. 80€","c. 23€",'a');
        library.setEnigmaE(libraryE);
        kitchen.setEnigmaW(libraryE);
        EnigmaOggetto kitchenE = new EnigmaOggetto(104,"Qui un'oggetto potrebbe aiutare ad avvitare la porta","Ottimo c'e' l'ho fatto","Peccato, mi sa che serve un altro oggetto",30);
        kitchen.setEnigmaE(kitchenE);
        hallway_5.setEnigmaW(kitchenE);
        EnigmaChiuso kitchenS = new EnigmaChiuso(105,"Il signor Rossi ha 4 figlie. Ogni figlia ha un fratello. Quanti figli ha in tutto?","a. 4","b. 5","c. 0",'b');
        kitchen.setEnigmaS(kitchenS);
        dininghall.setEnigmaN(kitchenS);
        EnigmaChiuso dininghallE = new EnigmaChiuso(106,"Qual è il numero successivo a 10 che è anche il doppio di 7?","a. 14","b. 9","c. 8",'a');
        dininghall.setEnigmaE(dininghallE);
        study.setEnigmaW(dininghallE);
        EnigmaChiuso dininghallS = new EnigmaChiuso(107,"Un computer con il suo mouse costa 110 euro. Il computer costa 100 	euro più del mouse. Quanto costa il computer e quanto costa il mouse?","a. 105 e 5","b. 102 e 8","c. 100 e 10",'a');
        dininghall.setEnigmaS(dininghallS);
        livingroom.setEnigmaN(dininghallS);
        EnigmaChiuso hallE = new EnigmaChiuso(108,"Un plotone di soldati è composto da 3 colonne e 15 righe. Le righe 	sono distanti tra loro di 2 metri. Quanto è lungo il plotone? ","a. 30","b. 32","c. 28",'c');
        hall.setEnigmaE(hallE);
        livingroom.setEnigmaW(hallE);
        EnigmaChiuso livingroomE = new EnigmaChiuso(109,"“Quante volte si può sottrarre un biscotto da una scatola con 100 	biscotti? ","a. 1","b. 100","c. 99",'a');
        livingroom.setEnigmaE(livingroomE);
        hallway_1.setEnigmaW(livingroomE);
        EnigmaChiuso studyS = new EnigmaChiuso(110,"Ho sei candele e ne spengo due. Quante ne rimangono?","a. 4","b. 6","c. 2",'b');
        study.setEnigmaS(studyS);
        hallway_1.setEnigmaN(studyS);
        EnigmaChiuso studyN = new EnigmaChiuso(111,"Se mi abbattete, sono tutto. Se mi tagli alla vita, non sono niente. Cosa sono? ","a. Candela","b. Serpente","c. 8",'c');
        study.setEnigmaN(studyN);
        hallway_5.setEnigmaS(studyN);
        EnigmaChiuso hallway5E = new EnigmaChiuso(112,"Ho i denti e proteggo casa, ma non mordo né abbaio... ","a. Cane","b. Chiave","c. Cancello",'b');
        hallway_5.setEnigmaE(hallway5E);
        hallway_6.setEnigmaW(hallway5E);
        EnigmaChiuso hallway1E = new EnigmaChiuso(113,"Un cavallo compie a ogni passo mezzo metro. Quanti passi dovrà fare per percorrere un chilometro?","a. 3526","b. 4000","c. 1500",'b');
        hallway_1.setEnigmaE(hallway1E);
        hallway_2.setEnigmaW(hallway1E);
        EnigmaChiuso hallway2E = new EnigmaChiuso(114,"Prende sempre le cose, sul serio. ","a. Mamma","b. Amico","c. Ladro",'c');
        hallway_2.setEnigmaE(hallway2E);
        hallway_3.setEnigmaW(hallway2E);
        EnigmaOggetto hallway6E = new EnigmaOggetto(115,"Questa stanza e' buia","Ora ci vedo meglio","Forse dovrei usare altro...",32);
        hallway_6.setEnigmaE(hallway6E);
        bathroom.setEnigmaW(hallway6E);
        EnigmaOggetto billiardroomS = new EnigmaOggetto(116,"Qui manca la maniglia!","Menomale che l'ho trovata","Dovro' continuare a cercare",33);
        billiardroom.setEnigmaS(billiardroomS);
        hallway_7.setEnigmaN(billiardroomS);
        EnigmaChiuso hallway3E = new EnigmaChiuso(117,"Il giocatore lo tira, il meccanico lo stringe, lo chef ce l’ha in cucina.","a. Dado","b. Carta","c. Chiave",'a');
        hallway_3.setEnigmaE(hallway3E);
        childbedroom.setEnigmaW(hallway3E);
        EnigmaChiuso hallway3S = new EnigmaChiuso(118,"Un topo e mezzo in un minuto e mezzo, mangiano un pezzo di formaggio e mezzo. Quanti topi servono per mangiare 60 pezzi di formaggio in 30 minuti","a. 1","b. 2","c. 3",'c');
        hallway_3.setEnigmaS(hallway3S);
        garden_2.setEnigmaN(hallway3S);
        EnigmaChiuso childbedroomS = new EnigmaChiuso(119,"Se lo hai lo puoi condividere, se lo condividi non lo hai","a. Letto","b. Pasto","c. Segreto",'c');
        childbedroom.setEnigmaS(childbedroomS);
        childbedroom.setEnigmaN(childbedroomS);
        EnigmaChiuso childbedroomN = new EnigmaChiuso(120,"Una lumaca vuole salire su un palo alto 5 metri; di giorno sale 3 metri e di notte scende 2 m. Quanto tempo ci metterà per raggiungere la cima del palo? ","a. 1 mese ","b. 2 ore","c. 5 giorni",'c');
        childbedroom.setEnigmaN(childbedroomN);
        hallway_7.setEnigmaS(childbedroomN);
        EnigmaOggetto hallway7E = new EnigmaOggetto(121,"Oh no un asse blocca la porta","Vai con l'Ascia","Forse qualcosa per tagliare sarebbe comodo",31);
        hallway_7.setEnigmaE(hallway7E);
        garden.setEnigmaW(hallway7E);
        
        //map
        entrance.setNorth(library);
        entrance.setEast(dininghall);
        entrance.setSouth(hall);
        library.setEast(kitchen);
        library.setSouth(entrance);
        kitchen.setWest(library);
        kitchen.setSouth(dininghall);
        kitchen.setEast(hallway_5);
        hallway_5.setWest(kitchen);
        hallway_5.setEast(hallway_6);
        hallway_5.setSouth(study);
        hallway_6.setWest(hallway_5);
        hallway_6.setEast(bathroom);
        bathroom.setWest(hallway_6);
        dininghall.setWest(entrance);
        dininghall.setNorth(kitchen);
        dininghall.setSouth(livingroom);
        dininghall.setEast(study);
        study.setWest(dininghall);
        study.setNorth(hallway_5);
        study.setSouth(hallway_1);
        hall.setNorth(entrance);
        hall.setEast(livingroom);
        livingroom.setWest(hall);
        livingroom.setNorth(dininghall);
        livingroom.setEast(hallway_1);
        hallway_1.setWest(livingroom);
        hallway_1.setEast(hallway_2);
        hallway_1.setNorth(study);
        hallway_2.setWest(hallway_1);
        hallway_2.setEast(hallway_3);
        hallway_3.setWest(hallway_2);
        hallway_3.setSouth(garden_2);
        hallway_3.setEast(childbedroom);
        garden_2.setNorth(hallway_3);
        childbedroom.setWest(hallway_3);
        childbedroom.setSouth(masterbedroom);
        childbedroom.setNorth(hallway_7);
        masterbedroom.setNorth(childbedroom);
        hallway_7.setSouth(childbedroom);
        hallway_7.setEast(garden);
        hallway_7.setNorth(billiardroom);
        garden.setWest(hallway_7);
        billiardroom.setSouth(hallway_7);
      
        //objects
        AdvObject screwdriver = new AdvObject(30, "Cacciavite", "Cacciavite, Potrebbe essere utile per svitare qualcosa.");
        screwdriver.setAlias(new String[]{"cacciavite"});
        screwdriver.setPushable(true);
        library.getObjects().add(screwdriver);
        AdvObject axe = new AdvObject(31, "Ascia", "Un'ascia, potrebbe aiutarmi a togliere un'asse.");
        axe.setAlias(new String[]{"ascia"});
        axe.setPushable(true);
        livingroom.getObjects().add(axe);
        AdvObject torch = new AdvObject(32, "Torcia", "Una torcia elettrica.");
        torch.setAlias(new String[]{"torcia"});
        torch.setPushable(true);
        kitchen.getObjects().add(torch);
        AdvObject handle = new AdvObject(33, "Maniglia", "Una maniglia che si e' staccata da una porta!");
        handle.setAlias(new String[]{"maniglia"});
        handle.setPushable(true);
        study.getObjects().add(handle);
        AdvObject key = new AdvObject(34, "Chiave", "Wow la chiave per uscire.");
        key.setAlias(new String[]{"chiave"});
        key.setPushable(true);
        garden.getObjects().add(key); 
        //set starting room
        this.setCurrentRoom(entrance);
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            boolean notResolved = false;
            int canMove = -1;
            if (p.getCommand().getType() == CommandType.NORD) {
            	canMove = getCurrentRoom().canNorth();
                if (canMove < 0 ) {
                	 noroom = true;
                	 lastEnigma = null;
                } else if(canMove == 0){
                	notResolved = true;
                	lastEnigma = getCurrentRoom().getEnigmaN();
                }else if (canMove == 1){
                	lastEnigma = null;
                	this.setCurrentRoom(getCurrentRoom().getNorth());
                	move = true;
                }
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
            	canMove = getCurrentRoom().canSouth();
                if (canMove < 0 ) {
               	 	noroom = true;
               	 	lastEnigma = null;
               }else if(canMove == 0){
               		notResolved = true;
               		lastEnigma = getCurrentRoom().getEnigmaS();
               }else if (canMove == 1){
            	   	lastEnigma = null;
               		this.setCurrentRoom(getCurrentRoom().getSouth());
               		move = true;
               }
            } else if (p.getCommand().getType() == CommandType.EAST) {
            	canMove = getCurrentRoom().canEast();
                if (canMove < 0 ) {
                	 noroom = true;
                	 lastEnigma = null;
                } else if(canMove == 0){
                	notResolved = true;
                	lastEnigma = getCurrentRoom().getEnigmaE();
                }else if (canMove == 1){
                	lastEnigma = null;
                	this.setCurrentRoom(getCurrentRoom().getEast());
                	move = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
            	canMove = getCurrentRoom().canWest();
                if (canMove < 0 ) {
                	 noroom = true;
                	 lastEnigma = null;
                } else if(canMove == 0){
                	notResolved = true;
                	lastEnigma = getCurrentRoom().getEnigmaW();
                }else if (canMove == 1){
                	lastEnigma = null;
                	this.setCurrentRoom(getCurrentRoom().getWest());
                	move = true;
                }
            } else if (p.getCommand().getType() == CommandType.INVENTORY) {
                out.println("Nel tuo inventario ci sono:");
                for (AdvObject o : getInventory()) {
                    out.println(o.getName() + ": " + o.getDescription());
                }
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                if (getCurrentRoom().getLook() != null) {
                    out.println(getCurrentRoom().getLook());
                } else {
                    out.println("Non c'è niente di interessante qui.");
                }
            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                if (p.getObject() != null) {
                    if (p.getObject().isPickupable()) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        out.println("Hai raccolto: " + p.getObject().getDescription());
                    } else {
                        out.println("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    out.println("Non c'è niente da raccogliere qui.");
                }
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                * Potrebbe non esssere la soluzione ottimale.
                 */
                if (p.getObject() == null && p.getInvObject() == null) {
                    out.println("Non c'è niente da aprire qui.");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof AdvObjectContainer) {
                                out.println("Hai aperto: " + p.getObject().getName());
                                AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getCurrentRoom().getObjects().add(next);
                                        out.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out.println();
                                }
                                p.getObject().setOpen(true);
                            } else {
                                out.println("Hai aperto: " + p.getObject().getName());
                                p.getObject().setOpen(true);
                            }
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                            if (p.getInvObject() instanceof AdvObjectContainer) {
                                AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getInventory().add(next);
                                        out.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out.println();
                                }
                                p.getInvObject().setOpen(true);
                            } else {
                                p.getInvObject().setOpen(true);
                            }
                            out.println("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } else if (p.getCommand().getType() == CommandType.USE) {
                //ricerca oggetti pushabili
                if (p.getObject() != null && p.getObject().isPushable()) {
                    out.println("Hai premuto: " + p.getObject().getName());
                    if (p.getObject().getId() == 34 && getCurrentRoom().getName()== "ingresso") {
                        end(out);
                    }else if(lastEnigma != null){
                    	lastEnigma.solve(p.getObject());
                    	if(lastEnigma.IsSolved()){
                			System.out.print("Porta Aperta! Ora puoi muoverti");
                			lastEnigma = null;
                		}
                    }
                } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
                    out.println("Hai premuto: " + p.getInvObject().getName());
                    if (p.getInvObject().getId() == 34) {
                        end(out);
                    }else if(lastEnigma != null){
                    	lastEnigma.solve(p.getInvObject());
                    	if(lastEnigma.IsSolved()){
                			System.out.print("Porta Aperta! Ora puoi muoverti");
                			lastEnigma = null;
                		}
                    }
                } else {
                    out.println("Non ci sono oggetti che puoi premere qui.");
                }
            }
            if (noroom) {
                out.println("Da quella parte non si può andare c'è un muro!");
            } else if(notResolved){
            	Scanner scan = new Scanner(System.in);  			
            	out.println("La porta è ancora chiusa!Vuoi risolvere l'enigma?");
            	String answer = scan.nextLine();
            
            	if(answer.equals("si")||answer.equals("SI")||answer.equals("yes")||answer.equals("YES") ) {
            		lastEnigma.solve(null);
            		if(lastEnigma.IsSolved()){
            			System.out.println("Porta Aperta! Ora puoi muoverti");
            			lastEnigma = null;
            		}
            	}else if(answer.equals("no")||answer.equals("NO")||answer.equals("No")) {
            		System.out.println("Puoi tornare a esplorare la casa!");
            	}
            }else if (move){
                out.println(getCurrentRoom().getName());
                out.println("================================================");
                out.println(getCurrentRoom().getDescription());
            }
        }
    }

    private void end(PrintStream out) {
        out.println("Sei Riuscito a fuggire congratulazioni!");
        System.exit(0);
    }
}
