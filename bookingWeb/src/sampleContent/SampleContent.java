package sampleContent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.CiudadDTO;
import model.HotelDTO;
import model.OfertaDescuentoDTO;
import model.PeriodoNoDisponibilidadDTO;
import model.ProvinciaDTO;
import model.TipoHabitacionDTO;

import org.apache.commons.validator.routines.*;

import view.Util;

import dao.DAOException;

import dao.jpa.FactoryDAOImpl;



public class SampleContent {

	/**
	 * @param args
	 * @throws DAOException 
	 */
	public static List<Long> ciudades;
	private enum TipoPeriodo{HOTEL,HABITACION}
	public SampleContent() throws DAOException {
		super();
		ciudades = FactoryDAOImpl.loadInstance().getCiudad().findAllCiudadCiudadId();
		System.out.println();
		// TODO Auto-generated constructor stub
	}

	
	public PeriodoNoDisponibilidadDTO randomPND(TipoPeriodo tipo)
	{
		Calendar fechas[]=null;
		String motivo="";
		switch (tipo) {
		case HOTEL:
			fechas=randomStartEndDate(365, 15, 30);
			motivo=randomMotivoHotel();
			break;
		case HABITACION:
			fechas=randomStartEndDate(60, 1, 15);
			motivo=randomMotivoHotel();
			break;
		}
		
		PeriodoNoDisponibilidadDTO pnd= new PeriodoNoDisponibilidadDTO();
		pnd.setFechaIni(fechas[0]);
		pnd.setFechaFin(fechas[1]);
		pnd.setMotivo(motivo);
		return pnd;
	}
	public Calendar[] randomStartEndDate(int maxDaysAfterToday,int minDays,int maxDays){
		Calendar startDate=randomFecha(maxDaysAfterToday);
		Calendar endDate=(Calendar) startDate.clone();
		int days=(int) Util.random(minDays, maxDays);
		endDate.add(Calendar.DATE, days);
		Calendar[] fechas= {startDate,endDate};
		return fechas;
	}
	
	public List <HotelDTO> randomHotels(int hoteles,CiudadDTO c) throws DAOException{
		List<HotelDTO> hotelLista=new ArrayList<HotelDTO>();
		for (int i = 0; i < hoteles; i++) {
			hotelLista.add(randomHotel(c));
		}
		return hotelLista;
		
	}
	
	public HotelDTO randomHotel(CiudadDTO c) throws DAOException{
		CiudadDTO ciudad;
		HotelDTO h =new HotelDTO();
		
		h.setEstrellas(randomStars());
		h.setNombre(randomNombreHotel());
		h.setDireccion(randomDireccionCompleta());
		h.setHabitaciones(randomHabitaciones(7));
		h.setPeriodosNoDisponibilidad(randomPNDs(2,TipoPeriodo.HOTEL));
		if(c==null){ 	ciudad=randomCity(); 		}
		else{ 			ciudad=c; 		}
		h.setCiudad(ciudad);
		// debe ir al final
		h.setDescripcion(randomDescripcion(h));
		return h;
	}
	public void persistHotel(HotelDTO h) throws DAOException{
	dao.jpa.Util.saveHotel(h);
	}
	public String randomDescripcion(HotelDTO hotel) throws DAOException{
		String provincia=FactoryDAOImpl.loadInstance().getProvincia().getProvinciabyCity(hotel.getCiudad()).getNombre();
		String estrellas=" y cuenta con " + hotel.getEstrellas() + " estrellas en su haber";
		if(hotel.getEstrellas()==0){estrellas="";}
String descripcion="El hotel "+ hotel.getNombre() + " es uno de los mejores hoteles de " + provincia +
" est� situado en la ciudad de " + hotel.getCiudad().getNombre() + estrellas + "."; 
	
		return descripcion;
		
	}
	
	
	public TipoHabitacionDTO randomHabitacion(){
		TipoHabitacionDTO th=new TipoHabitacionDTO();
		th.setCategoria(randomCategoriaHabitacion()+ " tipo "  + Util.random(1, 1000));
		th.setMaxPers((int) Util.random(1, 8));
		th.setNumHabitaciones((int) Util.random(10,100));
		th.setPrecio(randomPrecioHabitacion(th.getMaxPers()));
		th.setDescuentos(randomDescuentos(2));
		th.setPeriodosDeNoDisponibilidad(randomPNDs(2,TipoPeriodo.HABITACION));
		return th;
	}
	public List<TipoHabitacionDTO> randomHabitaciones(int habitaciones){
		List<TipoHabitacionDTO> habitacionLista=new ArrayList<TipoHabitacionDTO>();
		for (int i = 0; i < habitaciones; i++) {
			habitacionLista.add(randomHabitacion());
		}
		return habitacionLista;
	}
	public List<PeriodoNoDisponibilidadDTO> randomPNDs(int periodos,TipoPeriodo tipo){
		List<PeriodoNoDisponibilidadDTO> periodosLista=new ArrayList<PeriodoNoDisponibilidadDTO>();
		for (int i = 0; i < periodos; i++) {
			periodosLista.add(randomPND(tipo));
		}
		return periodosLista;
		}
	public List<OfertaDescuentoDTO> randomDescuentos(int descuentos){
		List<OfertaDescuentoDTO> descuentosLista=new ArrayList<OfertaDescuentoDTO>();
		for (int i = 0; i < descuentos; i++) {
			descuentosLista.add(randomDescuento());
		}
		return descuentosLista;
		
	}
	public Calendar randomFecha(int maxDaysAfterToday){
		Calendar c=Calendar.getInstance();
		
		c.add(Calendar.DATE, (int) Util.random(0, maxDaysAfterToday));
		
		c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
		return c;
	}
	public float randomPrecioHabitacion(int maxPers){
		float precio = Util.random(25, 90);
		return (precio*maxPers);
	}
	public OfertaDescuentoDTO randomDescuento(){
		OfertaDescuentoDTO od=new OfertaDescuentoDTO();
		Calendar[] fechas = randomStartEndDate(60,1,15);
		od.setFechaIni(fechas[0]);
		od.setFechaFin(fechas[1]);
		float rand=Util.random(1, 75);
		od.setPorcentaje(((float)rand/100.0F));
		return od;
	}
	public String randomCategoriaHabitacion(){
		String categorias[]={
				"Suite",
				"Suite presidencial",
				"Atico",
				"Normal",
				"Rom�ntica"
			
		};
		return categorias[(int) Util.random(0, categorias.length-1)];
	}
	public String randomMotivoHabitacion(){
		String motivos[]={
				"Labores de mantenimiento",
				"Reformas"
			
		};
		return motivos[(int) Util.random(0, motivos.length-1)];
	}
	public String randomMotivoHotel(){
		String motivos[]={
				"Labores de mantenimiento",
				"Reformas",
				"Fuera de temporada"
			
		};
		return motivos[(int) Util.random(0, motivos.length-1)];
	}
	public CiudadDTO randomCity() throws DAOException{
		long randomIndex=Util.random(0, ciudades.size()-1);
		long randomCiudadId=ciudades.get((int) randomIndex);
		CiudadDTO ciudad=FactoryDAOImpl.loadInstance().getCiudad().getCiudad(randomCiudadId);
		return ciudad;
	}
	public int randomStars(){return (int) Util.random(0, 5);}
	public String randomDireccionCompleta(){
		return randomTipoVia() + " " + randomCalle() + " N " + Util.random(1, 100);
	}
	public String randomNombreHotel(){
		int index=(int) Util.random(0, hoteles.length-1);
		String name=(String) hoteles[index][0];
		int number=(Integer) hoteles[index][1];
		hoteles[index][1]=(Integer)hoteles[index][1]+1;
		if(number<2){return name;}
		return name + " " + number; 
	}
	public String randomTipoVia(){
	
		String tiposDeVia[]={
				"Acceso",
				"Acera",
				"Alameda",
				"Autopista",
				"Autov�a",
				"Avenida",
				"C. Comercial",
				"Calle",
				"Callej�n",
				"Camino",
				"Ca�ada",
				"Carrer",
				"Carrera",
				"Carretera",
				"Cuesta",
				"Glorieta",
				"Pasadizo",
				"Pasaje",
				"Paseo",
				"Plaza",
				"Rambla",
				"Ronda",
				"Sendero",
				"Traves�a",
				"Urbanizaci�n",
				"V�a"
		};
		return tiposDeVia[(int) Util.random(0, tiposDeVia.length-1)];	
	}
	
	public String randomCalle(){
		String calles[]={
				"Akira Suzuki",
				"Andre Geim",
				"Christopher A. Pissarides",
				"Dale T. Mortensen",
				"Ei-ichi Negishi",
				"Konstantin Novoselov",
				"Liu Xiaobo",
				"Mario Vargas Llosa",
				"Peter A. Diamond",
				"Richard F. Heck",
				"Robert G. Edwards",
				"Ada E. Yonath",
				"Barack H. Obama",
				"Carol W. Greider",
				"Charles Kuen Kao",
				"Elinor Ostrom",
				"Elizabeth H. Blackburn",
				"George E. Smith",
				"Herta M�ller",
				"Jack W. Szostak",
				"Oliver E. Williamson",
				"Thomas A. Steitz",
				"Venkatraman Ramakrishnan",
				"Willard S. Boyle",
				"Fran�oise Barr�-Sinoussi",
				"Harald zur Hausen",
				"Jean-Marie Gustave Le Cl�zio",
				"Luc Montagnier",
				"Makoto Kobayashi",
				"Martin Chalfie",
				"Martti Ahtisaari",
				"Osamu Shimomura",
				"Paul Krugman",
				"Roger Y. Tsien",
				"Toshihide Maskawa",
				"Yoichiro Nambu",
				"Albert Arnold (Al) Gore Jr.",
				"Albert Fert",
				"Doris Lessing",
				"Eric S. Maskin",
				"Gerhard Ertl",
				"Leonid Hurwicz",
				"Mario R. Capecchi",
				"Oliver Smithies",
				"Peter Gr�nberg",
				"Roger B. Myerson",
				"Sir Martin J. Evans",
				"Andrew Z. Fire",
				"Craig C. Mello",
				"Edmund S. Phelps",
				"George F. Smoot",
				"John C. Mather",
				"Muhammad Yunus",
				"Orhan Pamuk",
				"Roger D. Kornberg",
				"Barry J. Marshall",
				"Harold Pinter",
				"J. Robin Warren",
				"John L. Hall",
				"Mohamed ElBaradei",
				"Richard R. Schrock",
				"Robert H. Grubbs",
				"Robert J. Aumann",
				"Roy J. Glauber",
				"Theodor W. H�nsch",
				"Thomas C. Schelling",
				"Yves Chauvin",
				"Aaron Ciechanover",
				"Avram Hershko",
				"David J. Gross",
				"Edward C. Prescott",
				"Elfriede Jelinek",
				"Finn E. Kydland",
				"Frank Wilczek",
				"H. David Politzer",
				"Irwin Rose",
				"Linda B. Buck",
				"Richard Axel",
				"Wangari Muta Maathai",
				"Alexei A. Abrikosov",
				"Anthony J. Leggett",
				"Clive W.J. Granger",
				"John M. Coetzee",
				"Paul C. Lauterbur",
				"Peter Agre",
				"Robert F. Engle III",
				"Roderick MacKinnon",
				"Shirin Ebadi",
				"Sir Peter Mansfield",
				"Vitaly L. Ginzburg",
				"Daniel Kahneman",
				"H. Robert Horvitz",
				"Imre Kert�sz",
				"Jimmy Carter",
				"John B. Fenn",
				"John E. Sulston",
				"Koichi Tanaka",
				"Kurt W�thrich",
				"Masatoshi Koshiba",
				"Raymond Davis Jr.",
				"Riccardo Giacconi",
				"Sydney Brenner",
				"Vernon L. Smith",
				"A. Michael Spence",
				"Carl E. Wieman",
				"Emil Adolf von Behring",
				"Eric A. Cornell",
				"George A. Akerlof",
				"Joseph E. Stiglitz",
				"K. Barry Sharpless",
				"Kofi Annan",
				"Leland H. Hartwell",
				"Ryoji Noyori",
				"Sir Paul M. Nurse",
				"Sir Vidiadhar Surajprasad Naipaul",
				"Tim Hunt",
				"William S. Knowles",
				"Wolfgang Ketterle",
				"Alan G. MacDiarmid",
				"Alan J. Heeger",
				"Arvid Carlsson",
				"Daniel L. McFadden",
				"Eric R. Kandel",
				"Gao Xingjian",
				"Herbert Kroemer",
				"Hideki Shirakawa",
				"Jack S. Kilby",
				"James J. Heckman",
				"Kim Dae-jung",
				"Paul Greengard",
				"Zhores I. Alferov",
				"Ahmed H. Zewail",
				"Gerardus 't Hooft",
				"G�nter Blobel",
				"G�nter Grass",
				"Martinus J.G. Veltman",
				"Robert A. Mundell",
				"Amartya Sen",
				"Daniel C. Tsui",
				"David Trimble",
				"Ferid Murad",
				"Horst L. St�rmer",
				"John A. Pople",
				"John Hume",
				"Jos� Saramago",
				"Louis J. Ignarro",
				"Robert B. Laughlin",
				"Robert F. Furchgott",
				"Walter Kohn",
				"Claude Cohen-Tannoudji",
				"Dario Fo",
				"Jens C. Skou",
				"Jody Williams",
				"John E. Walker",
				"Myron S. Scholes",
				"Paul D. Boyer",
				"Robert C. Merton",
				"Stanley B. Prusiner",
				"Steven Chu",
				"William D. Phillips",
				"Carlos Filipe Ximenes Belo",
				"David M. Lee",
				"Douglas D. Osheroff",
				"James A. Mirrlees",
				"Jos� Ramos-Horta",
				"Peter C. Doherty",
				"Richard E. Smalley",
				"Robert C. Richardson",
				"Robert F. Curl Jr.",
				"Rolf M. Zinkernagel",
				"Sir Harold W. Kroto",
				"William Vickrey",
				"Wislawa Szymborska",
				"Christiane N�sslein-Volhard",
				"Edward B. Lewis",
				"Eric F. Wieschaus",
				"F. Sherwood Rowland",
				"Frederick Reines",
				"Joseph Rotblat",
				"Mario J. Molina",
				"Martin L. Perl",
				"Paul J. Crutzen",
				"Robert E. Lucas Jr.",
				"Seamus Heaney",
				"Alfred G. Gilman",
				"Bertram N. Brockhouse",
				"Clifford G. Shull",
				"George A. Olah",
				"John C. Harsanyi",
				"John F. Nash Jr.",
				"Kenzaburo Oe",
				"Martin Rodbell",
				"Reinhard Selten",
				"Shimon Peres",
				"Yasser Arafat",
				"Yitzhak Rabin",
				"Douglass C. North",
				"Frederik Willem de Klerk",
				"Joseph H. Taylor Jr.",
				"Kary B. Mullis",
				"Michael Smith",
				"Nelson Mandela",
				"Phillip A. Sharp",
				"Richard J. Roberts",
				"Robert W. Fogel",
				"Russell A. Hulse",
				"Toni Morrison",
				"Derek Walcott",
				"Edmond H. Fischer",
				"Edwin G. Krebs",
				"Gary S. Becker",
				"Georges Charpak",
				"Rigoberta Mench� Tum",
				"Rudolph A. Marcus",
				"Aung San Suu Kyi",
				"Bert Sakmann",
				"Erwin Neher",
				"Nadine Gordimer",
				"Pierre-Gilles de Gennes",
				"Richard R. Ernst",
				"Ronald H. Coase",
				"E. Donnall Thomas",
				"Elias James Corey",
				"Harry M. Markowitz",
				"Henry W. Kendall",
				"Jerome I. Friedman",
				"Joseph E. Murray",
				"Merton H. Miller",
				"Mikhail Sergeyevich Gorbachev",
				"Octavio Paz",
				"Richard E. Taylor",
				"William F. Sharpe",
				"Camilo Jos� Cela",
				"Hans G. Dehmelt",
				"Harold E. Varmus",
				"J. Michael Bishop",
				"Norman F. Ramsey",
				"Sidney Altman",
				"The 14th Dalai Lama (Tenzin Gyatso)",
				"Thomas R. Cech",
				"Trygve Haavelmo",
				"Wolfgang Paul",
				"George H. Hitchings",
				"Gertrude B. Elion",
				"Hartmut Michel",
				"Jack Steinberger",
				"Johann Deisenhofer",
				"Leon M. Lederman",
				"Maurice Allais",
				"Melvin Schwartz",
				"Naguib Mahfouz",
				"Robert Huber",
				"Sir James W. Black",
				"Charles J. Pedersen",
				"Donald J. Cram",
				"J. Georg Bednorz",
				"Jean-Marie Lehn",
				"Joseph Brodsky",
				"K. Alexander M�ller",
				"Oscar Arias S�nchez",
				"Robert M. Solow",
				"Susumu Tonegawa",
				"Dudley R. Herschbach",
				"Elie Wiesel",
				"Ernst Ruska",
				"Gerd Binnig",
				"Heinrich Rohrer",
				"James M. Buchanan Jr.",
				"John C. Polanyi",
				"Rita Levi-Montalcini",
				"Stanley Cohen",
				"Wole Soyinka",
				"Yuan T. Lee",
				"Claude Simon",
				"Franco Modigliani",
				"Herbert A. Hauptman",
				"Jerome Karle",
				"Joseph L. Goldstein",
				"Klaus von Klitzing",
				"Michael S. Brown",
				"Carlo Rubbia",
				"C�sar Milstein",
				"Desmond Mpilo Tutu",
				"Georges J.F. K�hler",
				"Jaroslav Seifert",
				"Niels K. Jerne",
				"Richard Stone",
				"Robert Bruce Merrifield",
				"Simon van der Meer",
				"Barbara McClintock",
				"Gerard Debreu",
				"Henry Taube",
				"Lech Walesa",
				"Subramanyan Chandrasekhar",
				"William Alfred Fowler",
				"William Golding",
				"Aaron Klug",
				"Alfonso Garc�a Robles",
				"Alva Myrdal",
				"Bengt I. Samuelsson",
				"Gabriel Garc�a M�rquez",
				"George J. Stigler",
				"John R. Vane",
				"Kenneth G. Wilson",
				"Sune K. Bergstr�m",
				"Arthur Leonard Schawlow",
				"David H. Hubel",
				"Elias Canetti",
				"James Tobin",
				"Kai M. Siegbahn",
				"Kenichi Fukui",
				"Nicolaas Bloembergen",
				"Roald Hoffmann",
				"Roger W. Sperry",
				"Torsten N. Wiesel",
				"Adolfo P�rez Esquivel",
				"Baruj Benacerraf",
				"Czeslaw Milosz",
				"Frederick Sanger",
				"George D. Snell",
				"James Watson Cronin",
				"Jean Dausset",
				"Lawrence R. Klein",
				"Paul Berg",
				"Val Logsdon Fitch",
				"Walter Gilbert",
				"Abdus Salam",
				"Allan M. Cormack",
				"Georg Wittig",
				"Godfrey N. Hounsfield",
				"Herbert C. Brown",
				"Mother Teresa",
				"Odysseus Elytis",
				"Sheldon Lee Glashow",
				"Sir Arthur Lewis",
				"Steven Weinberg",
				"Theodore W. Schultz",
				"Arno Allan Penzias",
				"Daniel Nathans",
				"Hamilton O. Smith",
				"Herbert A. Simon",
				"Isaac Bashevis Singer",
				"Menachem Begin",
				"Mohamed Anwar al-Sadat",
				"Peter D. Mitchell",
				"Pyotr Leonidovich Kapitsa",
				"Robert Woodrow Wilson",
				"Werner Arber",
				"Andrew V. Schally",
				"Bertil Ohlin",
				"Ilya Prigogine",
				"James E. Meade",
				"John Hasbrouck van Vleck",
				"Philip Warren Anderson",
				"Roger Guillemin",
				"Rosalyn Yalow",
				"Sir Nevill Francis Mott",
				"Vicente Aleixandre",
				"Baruch S. Blumberg",
				"Betty Williams",
				"Burton Richter",
				"D. Carleton Gajdusek",
				"Mairead Corrigan",
				"Milton Friedman",
				"Samuel Chao Chung Ting",
				"Saul Bellow",
				"William N. Lipscomb",
				"Aage Niels Bohr",
				"Andrei Dmitrievich Sakharov",
				"Ben Roy Mottelson",
				"David Baltimore",
				"Eugenio Montale",
				"Howard Martin Temin",
				"John Warcup Cornforth",
				"Leo James Rainwater",
				"Leonid Vitaliyevich Kantorovich",
				"Renato Dulbecco",
				"Tjalling C. Koopmans",
				"Vladimir Prelog",
				"Albert Claude",
				"Antony Hewish",
				"Christian de Duve",
				"Eisaku Sato",
				"Eyvind Johnson",
				"Friedrich August von Hayek",
				"George E. Palade",
				"Gunnar Myrdal",
				"Harry Martinson",
				"Paul J. Flory",
				"Se�n MacBride",
				"Sir Martin Ryle",
				"Brian David Josephson",
				"Ernst Otto Fischer",
				"Geoffrey Wilkinson",
				"Henry A. Kissinger",
				"Ivar Giaever",
				"Karl von Frisch",
				"Konrad Lorenz",
				"Le Duc Tho",
				"Leo Esaki",
				"Nikolaas Tinbergen",
				"Patrick White",
				"Wassily Leontief",
				"Christian B. Anfinsen",
				"Gerald M. Edelman",
				"Heinrich B�ll",
				"John Bardeen",
				"John R. Hicks",
				"John Robert Schrieffer",
				"Kenneth J. Arrow",
				"Leon Neil Cooper",
				"Rodney R. Porter",
				"Stanford Moore",
				"William H. Stein",
				"Dennis Gabor",
				"Earl W. Sutherland, Jr.",
				"Gerhard Herzberg",
				"Pablo Neruda",
				"Simon Kuznets",
				"Willy Brandt",
				"Aleksandr Isayevich Solzhenitsyn",
				"Hannes Olof G�sta Alfv�n",
				"Julius Axelrod",
				"Louis Eug�ne F�lix N�el",
				"Luis F. Leloir",
				"Norman E. Borlaug",
				"Paul A. Samuelson",
				"Sir Bernard Katz",
				"Ulf von Euler",
				"Alfred D. Hershey",
				"Derek H. R. Barton",
				"Jan Tinbergen",
				"Max Delbr�ck",
				"Murray Gell-Mann",
				"Odd Hassel",
				"Ragnar Frisch",
				"Salvador E. Luria",
				"Samuel Beckett",
				"Har Gobind Khorana",
				"Lars Onsager",
				"Luis Walter Alvarez",
				"Marshall W. Nirenberg",
				"Ren� Cassin",
				"Robert W. Holley",
				"Yasunari Kawabata",
				"George Porter",
				"George Wald",
				"Haldan Keffer Hartline",
				"Hans Albrecht Bethe",
				"Manfred Eigen",
				"Miguel Angel Asturias",
				"Ragnar Granit",
				"Ronald George Wreyford Norrish",
				"Alfred Kastler",
				"Charles Brenton Huggins",
				"Nelly Sachs",
				"Peyton Rous",
				"Robert S. Mulliken",
				"Shmuel Yosef Agnon",
				"Andr� Lwoff",
				"Fran�ois Jacob",
				"Jacques Monod",
				"Julian Schwinger",
				"Mikhail Aleksandrovich Sholokhov",
				"Richard P. Feynman",
				"Robert Burns Woodward",
				"Sin-Itiro Tomonaga",
				"Aleksandr Mikhailovich Prokhorov",
				"Charles Hard Townes",
				"Dorothy Crowfoot Hodgkin",
				"Feodor Lynen",
				"Jean-Paul Sartre",
				"Konrad Bloch",
				"Martin Luther King Jr.",
				"Nicolay Gennadiyevich Basov",
				"Alan Lloyd Hodgkin",
				"Andrew Fielding Huxley",
				"Eugene Paul Wigner",
				"Giorgos Seferis",
				"Giulio Natta",
				"J. Hans D. Jensen",
				"Karl Ziegler",
				"Maria Goeppert Mayer",
				"Sir John Carew Eccles",
				"Francis Harry Compton Crick",
				"James Dewey Watson",
				"John Cowdery Kendrew",
				"John Steinbeck",
				"Lev Davidovich Landau",
				"Linus Carl Pauling",
				"Maurice Hugh Frederick Wilkins",
				"Max Ferdinand Perutz",
				"Dag Hjalmar Agne Carl Hammarskj�ld",
				"Georg von B�k�sy",
				"Ivo Andric",
				"Melvin Calvin",
				"Robert Hofstadter",
				"Rudolf Ludwig M�ssbauer",
				"Albert John Lutuli",
				"Donald Arthur Glaser",
				"Peter Brian Medawar",
				"Saint-John Perse",
				"Sir Frank Macfarlane Burnet",
				"Willard Frank Libby",
				"Arthur Kornberg",
				"Emilio Gino Segr�",
				"Jaroslav Heyrovsky",
				"Owen Chamberlain",
				"Philip J. Noel-Baker",
				"Salvatore Quasimodo",
				"Severo Ochoa",
				"Boris Leonidovich Pasternak",
				"Edward Lawrie Tatum",
				"Frederick Sanger",
				"George Wells Beadle",
				"Georges Pire",
				"Igor Yevgenyevich Tamm",
				"Il�ja Mikhailovich Frank",
				"Joshua Lederberg",
				"Pavel Alekseyevich Cherenkov",
				"Albert Camus",
				"Chen Ning Yang",
				"Daniel Bovet",
				"Lester Bowles Pearson",
				"Lord (Alexander R.) Todd",
				"Tsung-Dao (T.D.) Lee",
				"Andr� Fr�d�ric Cournand",
				"Dickinson W. Richards",
				"John Bardeen",
				"Juan Ram�n Jim�nez",
				"Nikolay Nikolaevich Semenov",
				"Sir Cyril Norman Hinshelwood",
				"Walter Houser Brattain",
				"Werner Forssmann",
				"William Bradford Shockley",
				"Axel Hugo Theodor Theorell",
				"Halld�r Kiljan Laxness",
				"Polykarp Kusch",
				"Vincent du Vigneaud",
				"Willis Eugene Lamb",
				"Ernest Miller Hemingway",
				"Frederick Chapman Robbins",
				"John Franklin Enders",
				"Linus Carl Pauling",
				"Max Born",
				"Thomas Huckle Weller",
				"Walther Bothe",
				"Frits (Frederik) Zernike",
				"Fritz Albert Lipmann",
				"George Catlett Marshall",
				"Hans Adolf Krebs",
				"Hermann Staudinger",
				"Sir Winston Leonard Spencer Churchill",
				"Albert Schweitzer",
				"Archer John Porter Martin",
				"Edward Mills Purcell",
				"Felix Bloch",
				"Fran�ois Mauriac",
				"Richard Laurence Millington Synge",
				"Selman Abraham Waksman",
				"Edwin Mattison McMillan",
				"Ernest Thomas Sinton Walton",
				"Glenn Theodore Seaborg",
				"L�on Jouhaux",
				"Max Theiler",
				"P�r Fabian Lagerkvist",
				"Sir John Douglas Cockcroft",
				"Cecil Frank Powell",
				"Earl (Bertrand Arthur William) Russell",
				"Edward Calvin Kendall",
				"Kurt Alder",
				"Otto Paul Hermann Diels",
				"Philip Showalter Hench",
				"Ralph Bunche",
				"Tadeus Reichstein",
				"Antonio Caetano de Abreu Freire Egas Moniz",
				"Hideki Yukawa",
				"Lord (John) Boyd Orr of Brechin",
				"Walter Rudolf Hess",
				"William Faulkner",
				"William Francis Giauque",
				"Arne Wilhelm Kaurin Tiselius",
				"Patrick Maynard Stuart Blackett",
				"Paul Hermann M�ller",
				"Thomas Stearns Eliot",
				"Andr� Paul Guillaume Gide",
				"Bernardo Alberto Houssay",
				"Carl Ferdinand Cori",
				"Gerty Theresa Cori, n�e Radnitz",
				"Sir Edward Victor Appleton",
				"Sir Robert Robinson",
				"Emily Greene Balch",
				"Hermann Hesse",
				"Hermann Joseph Muller",
				"James Batcheller Sumner",
				"John Howard Northrop",
				"John Raleigh Mott",
				"Percy Williams Bridgman",
				"Wendell Meredith Stanley",
				"Artturi Ilmari Virtanen",
				"Cordell Hull",
				"Ernst Boris Chain",
				"Gabriela Mistral",
				"Sir Alexander Fleming",
				"Sir Howard Walter Florey",
				"Wolfgang Pauli",
				"Herbert Spencer Gasser",
				"Isidor Isaac Rabi",
				"Johannes Vilhelm Jensen",
				"Joseph Erlanger",
				"Otto Hahn",
				"Edward Adelbert Doisy",
				"George de Hevesy",
				"Henrik Carl Peter Dam",
				"Otto Stern",
				"Adolf Friedrich Johann Butenandt",
				"Ernest Orlando Lawrence",
				"Frans Eemil Sillanp��",
				"Gerhard Domagk",
				"Leopold Ruzicka",
				"Corneille Jean Fran�ois Heymans",
				"Enrico Fermi",
				"Pearl Buck",
				"Richard Kuhn",
				"Albert von Szent-Gy�rgyi Nagyr�polt",
				"Cecil of Chelwood, Viscount (Lord Edgar Algernon Robert Gascoyne Cecil)",
				"Clinton Joseph Davisson",
				"George Paget Thomson",
				"Paul Karrer",
				"Roger Martin du Gard",
				"Walter Norman Haworth",
				"Carl David Anderson",
				"Carlos Saavedra Lamas",
				"Eugene Gladstone O'Neill",
				"Otto Loewi",
				"Petrus (Peter) Josephus Wilhelmus Debye",
				"Sir Henry Hallett Dale",
				"Victor Franz Hess",
				"Carl von Ossietzky",
				"Fr�d�ric Joliot",
				"Hans Spemann",
				"Ir�ne Joliot-Curie",
				"James Chadwick",
				"Arthur Henderson",
				"George Hoyt Whipple",
				"George Richards Minot",
				"Harold Clayton Urey",
				"Luigi Pirandello",
				"William Parry Murphy",
				"Erwin Schr�dinger",
				"Ivan Alekseyevich Bunin",
				"Paul Adrien Maurice Dirac",
				"Sir Norman Angell (Ralph Lane)",
				"Thomas Hunt Morgan",
				"Edgar Douglas Adrian",
				"Irving Langmuir",
				"John Galsworthy",
				"Sir Charles Scott Sherrington",
				"Werner Karl Heisenberg",
				"Carl Bosch",
				"Erik Axel Karlfeldt",
				"Friedrich Bergius",
				"Jane Addams",
				"Nicholas Murray Butler",
				"Otto Heinrich Warburg",
				"Hans Fischer",
				"Karl Landsteiner",
				"Lars Olof Jonathan (Nathan) S�derblom",
				"Sinclair Lewis",
				"Sir Chandrasekhara Venkata Raman",
				"Arthur Harden",
				"Christiaan Eijkman",
				"Frank Billings Kellogg",
				"Hans Karl August Simon von Euler-Chelpin",
				"Prince Louis-Victor Pierre Raymond de Broglie",
				"Sir Frederick Gowland Hopkins",
				"Thomas Mann",
				"Adolf Otto Reinhold Windaus",
				"Charles Jules Henri Nicolle",
				"Owen Willans Richardson",
				"Sigrid Undset",
				"Arthur Holly Compton",
				"Charles Thomson Rees Wilson",
				"Ferdinand Buisson",
				"Heinrich Otto Wieland",
				"Henri Bergson",
				"Julius Wagner-Jauregg",
				"Ludwig Quidde",
				"Aristide Briand",
				"Grazia Deledda",
				"Gustav Stresemann",
				"Jean Baptiste Perrin",
				"Johannes Andreas Grib Fibiger",
				"The (Theodor) Svedberg",
				"Charles Gates Dawes",
				"George Bernard Shaw",
				"Gustav Ludwig Hertz",
				"James Franck",
				"Richard Adolf Zsigmondy",
				"Sir Austen Chamberlain",
				"Karl Manne Georg Siegbahn",
				"Willem Einthoven",
				"Wladyslaw Stanislaw Reymont",
				"Frederick Grant Banting",
				"Fritz Pregl",
				"John James Rickard Macleod",
				"Robert Andrews Millikan",
				"William Butler Yeats",
				"Archibald Vivian Hill",
				"Francis William Aston",
				"Fridtjof Nansen",
				"Jacinto Benavente",
				"Niels Henrik David Bohr",
				"Otto Fritz Meyerhof",
				"Albert Einstein",
				"Anatole France",
				"Christian Lous Lange",
				"Frederick Soddy",
				"Karl Hjalmar Branting",
				"Charles Edouard Guillaume",
				"Knut Pedersen Hamsun",
				"L�on Victor Auguste Bourgeois",
				"Schack August Steenberg Krogh",
				"Walther Hermann Nernst",
				"Carl Friedrich Georg Spitteler",
				"Johannes Stark",
				"Jules Bordet",
				"Thomas Woodrow Wilson",
				"Fritz Haber",
				"Max Karl Ernst Ludwig Planck",
				"Charles Glover Barkla",
				"Henrik Pontoppidan",
				"Karl Adolph Gjellerup",
				"Carl Gustaf Verner von Heidenstam",
				"Richard Martin Willst�tter",
				"Romain Rolland",
				"Sir William Henry Bragg",
				"William Lawrence Bragg",
				"Max von Laue",
				"Robert B�r�ny",
				"Theodore William Richards",
				"Alfred Werner",
				"Charles Robert Richet",
				"Heike Kamerlingh Onnes",
				"Henri La Fontaine",
				"Rabindranath Tagore",
				"Alexis Carrel",
				"Elihu Root",
				"Gerhart Johann Robert Hauptmann",
				"Nils Gustaf Dal�n",
				"Paul Sabatier",
				"Victor Grignard",
				"Alfred Hermann Fried",
				"Allvar Gullstrand",
				"Count Maurice (Mooris) Polidore Marie Bernhard Maeterlinck",
				"Marie Curie, n�e Sklodowska",
				"Tobias Michael Carel Asser",
				"Wilhelm Wien",
				"Albrecht Kossel",
				"Johannes Diderik van der Waals",
				"Otto Wallach",
				"Paul Johann Ludwig Heyse",
				"Auguste Marie Fran�ois Beernaert",
				"Emil Theodor Kocher",
				"Guglielmo Marconi",
				"Karl Ferdinand Braun",
				"Paul Henri Benjamin Balluet d'Estournelles de Constant, Baron de Constant de Rebecque",
				"Selma Ottilia Lovisa Lagerl�f",
				"Wilhelm Ostwald",
				"Ernest Rutherford",
				"Fredrik Bajer",
				"Gabriel Lippmann",
				"Ilya Ilyich Mechnikov",
				"Klas Pontus Arnoldson",
				"Paul Ehrlich",
				"Rudolf Christoph Eucken",
				"Albert Abraham Michelson",
				"Charles Louis Alphonse Laveran",
				"Eduard Buchner",
				"Ernesto Teodoro Moneta",
				"Louis Renault",
				"Rudyard Kipling",
				"Camillo Golgi",
				"Giosu� Carducci",
				"Henri Moissan",
				"Joseph John Thomson",
				"Santiago Ram�n y Cajal",
				"Theodore Roosevelt",
				"Baroness Bertha Sophie Felicita von Suttner, n�e Countess Kinsky von Chinic und Tettau",
				"Henryk Sienkiewicz",
				"Johann Friedrich Wilhelm Adolf von Baeyer",
				"Philipp Eduard Anton von Lenard",
				"Robert Koch",
				"Fr�d�ric Mistral",
				"Ivan Petrovich Pavlov",
				"Jos� Echegaray y Eizaguirre",
				"Lord Rayleigh (John William Strutt)",
				"Sir William Ramsay",
				"Antoine Henri Becquerel",
				"Bj�rnstjerne Martinus Bj�rnson",
				"Marie Curie, n�e Sklodowska",
				"Niels Ryberg Finsen",
				"Pierre Curie",
				"Svante August Arrhenius",
				"William Randal Cremer",
				"Charles Albert Gobat",
				"Christian Matthias Theodor Mommsen",
				"�lie Ducommun",
				"Hendrik Antoon Lorentz",
				"Hermann Emil Fischer",
				"Pieter Zeeman",
				"Ronald Ross",
				"Fr�d�ric Passy",
				"Jacobus Henricus van 't Hoff",
				"Jean Henry Dunant",
				"Sully Prudhomme",
				"Wilhelm Conrad R�ntgen"
				
		};
		return calles[(int) Util.random(0, calles.length-1)];
		
	}
	
	public static Object hoteles[][]={
		{"NH Amistad",0},
		{"Juan Carlos I",0},
		{"Nelva",0},
		{"Silken 7 coronas",0},
		{"Novotel",0},
		{"Carmen",0},
		{"Marquesa",0},
		{"Duquesa",0},
		{"Hesperia",0},
		{"Plaza",0},
		{"Hyat",0},
		{"Royal Palace",0},
		{"Palace",0},
		{"Sheraton",0},
		{"Hilton",0},
		{"NH Rinc�n de Pepe",0},
		{"Catalonia Conde de Floridablanca",0}, 
		{"Hispano",0},
		{"Zenit",0},
		{"Campanile",0},
		{"Legazpi",0},
		{"Casa Emilio",0},
		{"Rosa Victoria",0},
		{"Ibis",0},
		{"Ritz",0},
		{"Rex",0},
		{"Emperador",0},
		{"Silken PuertaAmerica",0},
		{"Gran Meli�",0},
		{"Gran Versalles",0},
		

};
}
