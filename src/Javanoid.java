import java.awt.*;
     import java.awt.event.*;
     import java.applet.Applet;
     import java.awt.Graphics;
     import java.lang.Math;
     import java.util.*;

public class  Javanoid extends Applet implements Runnable{
private static int RAYON=10;
private static int RAPIDITE=5;
private static int LARGEUR=500;
private static int HAUTEUR=600;
    private static final long serialVersionUID = 1L;

public Cadre tableau;
public ListeBoule listeballe;
public Boule balle;
public Barre tige;
public Level level;
public Brique brique;
public Option options;
public Bonus  GetBonus,SetBonus;
public ListeBonus listebonus;
public ListeLaser listelaser;
public Laser laser;
public static int Score;
public boolean ToucheBarre;
public Thread runner= new Thread(this);
public TracerSouris s=new TracerSouris();

@Override
    public void init()
    {
      tableau=new Cadre(80,10);
      tige=new Barre(220,tableau);
      balle=new Boule(tige,1,1);
      listeballe=new ListeBoule();
      listelaser=new ListeLaser();
      level=new Level(tableau.Getx()+(getLARGEUR()-getHAUTEUR()/2)/2,tableau.Gety()+(2*getHAUTEUR()/3-getLARGEUR()/2)/2);
      options=new Option(tableau.GetBordDroit(),tableau.GetBordHaut(),tableau.GetLargeur()/3,tableau.GetHauteur());
      options.SetColler();
      listebonus=new ListeBonus();
      listeballe.insererEnTete(balle);
      level.Level2();
      ToucheBarre=true;
      setBackground(Color.white);
      addMouseMotionListener(s);
      addMouseListener(s);
      runner.start();
     }

@Override
@SuppressWarnings("empty-statement")
    public  void run(){
    Graphics g=getGraphics();
    if(!level.LevelFini()){
    if(options.GetVie()>=0){
    if(!listeballe.estVide()){
    if(!(options.GetColler() && ToucheBarre) || listeballe.ContientPlusDuneBoule()){
    if(options.GetDoubleBarre() && tige.GetLargeur()<(tableau.GetLargeur()/5)){
    options.NoDoubleBarre();
    tige=new Barre(tige.Getx(),tige.Gety(),tige.GetHauteur(),tige.GetLargeur()*2);
    }else {options.NoDoubleBarre();}
    if(options.GetMultiBalle()){
    options.NoMultiballe();
    balle=new Boule(tige,1,1);
    listeballe.insererEnTete(balle);}

    ToucheBarre=listeballe.TestBarre(tige);
    listeballe.TestFall(tige);
    listeballe.TestCadre(tableau);
    if(options.GetInvincible()){brique=listeballe.getPremier().TestBriqueInvincible(level,Score);
                                listeballe.TestBriqueInvincible(level,Score);}
                                else {     brique=listeballe.getPremier().TestBrique(level,Score);
                                listeballe.TestBrique(level,Score);
                                }
    GetBonus=listebonus.TestBarre(tige);
    options.Bonus_to_Option(GetBonus);
    listebonus.Fall(tige);
    if(brique!=null){
    SetBonus=new Bonus(brique);
    SetBonus.Random();
    listebonus.insererEnQueue(SetBonus);
    options.addScore();
    }

    listelaser.TestBrique(level,Score);
    listelaser.TestCadre(tableau);
    listebonus.Move();
    listeballe.Move();
    listelaser.Move();
    listelaser.Dessiner(g);
    listeballe.Dessiner(g);
    listebonus.Dessiner(g);
    try {Thread.sleep(30/   getRAPIDITE());}
           catch(InterruptedException e){};

    repaint();
    }} else {this.Vieperdu(g); }
    }
    }else{  this.DessinerFin(g);}
    }

@Override
    public void update(Graphics g) {
    paint(g);
    }

@Override
    public void paint(Graphics g){
    String tmp,tmp2;
    tableau.Dessiner(g);
    tige.Dessiner(g);
    listebonus.EnDessiner(g);
    level.Dessiner(g);
    listeballe.EnDessiner(g);
    options.EnDessiner(g);
    options.Dessiner(g);
    listelaser.EnDessiner(g);
    if(options.GetColler() && ToucheBarre){listeballe.Dessiner(g);}
    this.run();
    }

     public void Vieperdu(Graphics g){
             tige.EnDessiner(g);
             tige=new Barre(220,tableau);
             listelaser=new ListeLaser();
             balle=new Boule(tige,1,1);
             listeballe.insererEnTete(balle);
             options.supVie();
             ToucheBarre=true;
             options.Init();}

    public void DessinerFin(Graphics g){
    removeMouseMotionListener(s);
    removeMouseListener(s);
    g.setColor(Color.white);
    g.fillRect(tableau.Getx(),tableau.Gety(),tableau.GetLargeur()+options.GetCadre().GetLargeur()+1,tige.GetBordBas());
    g.setColor(Color.black);
    g.drawString("Score : "+(options.GetScore()*100+options.GetVie()*1000),tableau.GetBordGauche()+(tableau.GetLargeur()/2),tableau.GetBordHaut()+(tableau.GetHauteur()/2));
    }

    class TracerSouris extends MouseAdapter implements MouseMotionListener{

    @Override
    public void mouseDragged(MouseEvent e){}

    @Override
    public void mouseMoved(MouseEvent e){
    int ancvaleur=tige.GetBordGauche();
    int tmp=e.getX();
    if(tmp<=tableau.GetBordGauche()){tmp=tableau.GetBordGauche();}
    if(tmp+tige.GetLargeur()>=tableau.GetBordDroit()){tmp=tableau.GetBordDroit()-tige.GetLargeur();}
    Graphics g=getGraphics();
    tige.EnDessiner(g);
    tige.Givex(tmp);
    repaint();
    if(options.GetColler() && ToucheBarre){
    listeballe.EnDessiner(g);
    listeballe.getPremier().Move(tmp-ancvaleur,0);
    }
    }

    @Override
    public void mouseClicked(MouseEvent e){
    Graphics g=getGraphics();
    if(options.GetColler()==true && ToucheBarre) {
        options.DeColler();
    }
    if(options.GetLaser()){laser=new Laser(tige);listelaser.insererEnTete(laser);}
    repaint();}

    }

    /**
     * @return the RAYON
     */
    public static int getRAYON() {
        return RAYON;
    }

    /**
     * @param aRAYON the RAYON to set
     */
    public static void setRAYON(int aRAYON) {
        RAYON = aRAYON;
    }

    /**
     * @return the RAPIDITE
     */
    public static int getRAPIDITE() {
        return RAPIDITE;
    }

    /**
     * @param aRAPIDITE the RAPIDITE to set
     */
    public static void setRAPIDITE(int aRAPIDITE) {
        RAPIDITE = aRAPIDITE;
    }

    /**
     * @return the LARGEUR
     */
    public static int getLARGEUR() {
        return LARGEUR;
    }

    /**
     * @param aLARGEUR the LARGEUR to set
     */
    public static void setLARGEUR(int aLARGEUR) {
        LARGEUR = aLARGEUR;
    }

    /**
     * @return the HAUTEUR
     */
    public static int getHAUTEUR() {
        return HAUTEUR;
    }

    /**
     * @param aHAUTEUR the HAUTEUR to set
     */
    public static void setHAUTEUR(int aHAUTEUR) {
        HAUTEUR = aHAUTEUR;
    }

}

    class ListeLaser{
     private Chainon premier;

    private class Chainon{
    private Laser data;
    private Chainon suivant;
    }

    public ListeLaser(){
    premier=null;}

    public boolean estVide(){
    return (premier==null);}

    public Laser getPremier(){
    if(estVide()) {
        throw new NoSuchElementException();
    }
        return premier.data;
    }

    public void insererEnTete(Laser data){
    Chainon nvChn=new Chainon();
    nvChn.data=data;
    Chainon nvChn1=new Chainon();
    nvChn1.data=new Laser(data);
    nvChn1.suivant=premier;
    nvChn.suivant=nvChn1;
    premier=nvChn;}

    public void insererEnQueue(Laser data){
    Chainon curseur=new Chainon();
    Chainon nvChn=new Chainon();
    nvChn.data=data;
    curseur=premier;
    if(estVide()){this.insererEnTete(data);}
    else{
    while(curseur.suivant!=null){
    curseur=curseur.suivant;}
    curseur.suivant=nvChn;}
    }

    public void retrancherEnTete(){
    if(estVide()) {
        throw new NoSuchElementException();
    } else{premier=premier.suivant;}}

    public void supprimerElt(Chainon laser){
    Chainon curseur=new Chainon();
    curseur=premier;
    if(curseur==null) {
        throw new NoSuchElementException();
    }

    if(curseur.data.Getx()==laser.data.Getx()){this.retrancherEnTete();}
    else{
    while(curseur.suivant.data.Getx()!=laser.data.Getx() && curseur.suivant.data.Gety()!=laser.data.Gety() && curseur.suivant!=null){
    curseur=curseur.suivant;}
    curseur.suivant=curseur.suivant.suivant;}
    }

    public void TestBrique(Level level,int score){
    Chainon curseur=new Chainon();
    curseur=premier;
    while(curseur!=null){
    if(curseur.data.TestBrique(level,score)){supprimerElt(curseur);}
    curseur=curseur.suivant;
    }
    }

    public void TestCadre(Cadre tableau){
    Chainon curseur=new Chainon();
    curseur=premier;
    while(curseur!=null){
    if(curseur.data.TestCadre(tableau)){supprimerElt(curseur);}
    curseur=curseur.suivant;
    }
    }

    public void Move(){
    Chainon curseur=new Chainon();
    curseur=premier;
     while(curseur!=null){
    curseur.data.Move();
    curseur=curseur.suivant;
    }
    }

    public void Dessiner(Graphics g){
    Chainon curseur=new Chainon();
    curseur=premier;
     while(curseur!=null){
    curseur.data.Dessiner(g);
    curseur=curseur.suivant;
    }
    }

    public void EnDessiner(Graphics g){
    Chainon curseur=new Chainon();
    curseur=premier;
     while(curseur!=null){
    curseur.data.EnDessiner(g);
    curseur=curseur.suivant;
    }
    }
}
    class Laser{
    private int x;
    private int y;
    private int largeur;
    private int hauteur;

    public Laser(int x,int y,int largeur,int hauteur){
    this.x=x;
    this.y=y;
    this.largeur=largeur;
    this.hauteur=hauteur;
    }

    public Laser(Barre tige){
    this.x=tige.Getx();
    this.y=tige.Gety();
    this.largeur=tige.GetLargeur()/20;
    this.hauteur=tige.GetHauteur();}

    public Laser(Laser laser){
    this.x=laser.Getx()+(19*laser.GetLargeur());
    this.y=laser.Gety();
    this.largeur=laser.GetLargeur();
    this.hauteur=laser.GetHauteur();}

    public int Getx(){
    return this.x;}

    public int Gety(){
    return this.y;}

    public int GetHauteur(){
    return this.hauteur;}

    public int GetLargeur(){
    return this.largeur;}

    public void Move(){
    this.y -= 2;}

    public void Move(int y){
    this.y -= y;}

    public boolean TestBrique(Level level,int score){
    int i=0,j=0;
    if(this.Getx()+this.largeur>=level.GetBordGauche() && this.Getx()-this.largeur<=level.GetBordDroit() && this.Gety()-this.largeur<=level.GetBordBas() && this.Gety()+this.largeur>=level.GetBordHaut()){
    j= (this.Getx()-level.GetBordGauche())/(level.GetMatrice()[0][0].GetLargeur()+1);
    i= (this.Gety()-level.GetBordHaut())/(level.GetMatrice()[0][0].GetHauteur()+1);

    if(i<level.GetMatrice().length){
           if(j<level.GetMatrice()[i].length){

           if(level.GetMatrice()[i][j].GetExiste()){

           level.GetMatrice()[i][j].Casser();
           ++score;return true;}
           }
           }
    }
    return false;
    }

    public boolean TestCadre(Cadre tableau){
    if(this.Gety()<=tableau.GetBordHaut()){return true;}
    return false;}

    public void Dessiner(Graphics g){
    g.setColor(Color.red);
    g.fillRect(this.x,this.y,this.largeur,this.hauteur);}

    public void EnDessiner(Graphics g){
    g.setColor(Color.white);
    g.fillRect(this.x,this.y,this.largeur,this.hauteur);}

    }

    class ListeBoule {
    private Chainon premier;

    private class Chainon{
    private Boule data;
    private Chainon suivant;
    }

    public ListeBoule(){
    premier=null;}

    public boolean estVide(){
    return (premier==null);}

    public boolean ContientPlusDuneBoule(){
    if(estVide()){return false;}
    if(premier.suivant==null){return false;}
    return true;}

    public Boule getPremier(){
    if(estVide()) {
        throw new NoSuchElementException();
    }
        return premier.data;
    }

    public void insererEnTete(Boule data){
    Chainon nvChn=new Chainon();
    nvChn.data=data;
    nvChn.suivant=premier;
    premier=nvChn;}

    public void insererEnQueue(Boule data){
    Chainon curseur=new Chainon();
    Chainon nvChn=new Chainon();
    nvChn.data=data;
    curseur=premier;
    if(estVide()){this.insererEnTete(data);}
    else{
    while(curseur.suivant!=null){
    curseur=curseur.suivant;}
    curseur.suivant=nvChn;}
    }

    public void retrancherEnTete(){
    if(estVide()) {
        throw new NoSuchElementException();
    } else{premier=premier.suivant;}}

    public void supprimerElt(Chainon balle){
    Chainon curseur=new Chainon();
    curseur=premier;
    if(curseur==null) {
        throw new NoSuchElementException();
    }

    if(curseur.data.Getx()==balle.data.Getx()){this.retrancherEnTete();}
    else{
    while(curseur.suivant.data.Getx()!=balle.data.Getx() && curseur.suivant.data.Gety()!=balle.data.Gety() && curseur.suivant!=null){
    curseur=curseur.suivant;}
    curseur.suivant=curseur.suivant.suivant;}
    }

    public void TestFall(Barre tige){
    Chainon curseur=new Chainon();
    curseur=premier;
    while(curseur!=null){
    if(curseur.data.TestFall(tige)){this.supprimerElt(curseur);}
    curseur=curseur.suivant;
    }
    }

    public boolean TestBarre(Barre tige){
    boolean touche=false;
    Chainon curseur=new Chainon();
    curseur=premier;
    while(curseur!=null){
    if(curseur==premier){touche=curseur.data.TestBarre(tige);}
    else{curseur.data.TestBarre(tige);}
    curseur=curseur.suivant;

    }
    return touche;
    }

    public void TestBrique(Level level,int score){
    Chainon curseur=new Chainon();
    curseur=premier;
    while(curseur!=null){
    curseur.data.TestBrique(level,score);
    curseur=curseur.suivant;
    }
    }

    public void TestCadre(Cadre tableau){
    Chainon curseur=new Chainon();
    curseur=premier;
    while(curseur!=null){
    curseur.data.TestCadre(tableau);
    curseur=curseur.suivant;
    }
    }

    public void TestBriqueInvincible(Level level,int score){
    Chainon curseur=new Chainon();
    curseur=premier;
    while(curseur!=null){
    curseur.data.TestBriqueInvincible(level,score);
    curseur=curseur.suivant;
    }
    }

    public void Move(){
    Chainon curseur=new Chainon();
    curseur=premier;
     while(curseur!=null){
    curseur.data.Move();
    curseur=curseur.suivant;
    }
    }

    public void Dessiner(Graphics g){
    Chainon curseur=new Chainon();
    curseur=premier;
     while(curseur!=null){
    curseur.data.Dessiner(g);
    curseur=curseur.suivant;
    }
    }

    public void EnDessiner(Graphics g){
    Chainon curseur=new Chainon();
    curseur=premier;
     while(curseur!=null){
    curseur.data.EnDessiner(g);
    curseur=curseur.suivant;
    }
    }
}

    class Option{
   public static int RAYON=10;
   public static int RAPIDITE=5;
   public static int LARGEUR=500;
   public static int HAUTEUR=600;

    private boolean invincible;
    private boolean doublebarre;
    private boolean multiballe;
    private int vie;
    private boolean laser;
    private boolean coller;
    private int Score;
    private Cadre cadre;

    public Option(){
    this.cadre=new Cadre();
    this.invincible=false;
    this.doublebarre=false;
    this.multiballe=false;
    this.vie=3;
    this.laser=false;
    this.coller=false;
    this.Score=0;
    }

    public Option(int x,int y,int largeur,int hauteur){
    this.cadre=new Cadre(x,y,largeur,hauteur);
    this.invincible=false;
    this.doublebarre=false;
    this.multiballe=false;
    this.vie=3;
    this.laser=false;
    this.coller=false;
    this.Score=0;
    }

    public void Init(){
    this.invincible=false;
    this.doublebarre=false;
    this.multiballe=false;
    this.laser=false;
    this.coller=true;
    }

    public Cadre GetCadre(){
    return this.cadre;}

    public int GetVie(){
    return this.vie;}

    public int GetScore(){
    return this.Score;}

    public boolean GetMultiBalle(){
    return this.multiballe;}

    public boolean GetInvincible(){
    return this.invincible;}

    public boolean GetDoubleBarre(){
    return this.doublebarre;}

    public boolean GetLaser(){
    return this.laser;}

    public boolean GetColler(){
    return this.coller;}

    public void DeColler(){
    this.coller=false;}

    public void SetCadre(int x,int y,int largeur,int hauteur){
    this.cadre=new Cadre(x,y,largeur,hauteur);}

    public void SetVie(int x){
    this.vie=x;}

    public void addVie(){
    ++this.vie;}

    public void supVie(){
    --this.vie;}

    public void SetScore(int x){
    this.Score=x;}

    public void addScore(){
    ++this.Score;}

    public void addScore(int x){
    this.Score += x;}

    public void SetMultiballe(){
    this.multiballe=true;}

    public void NoMultiballe(){
    this.multiballe=false;}

    public void SetInvincible(){
    this.invincible=true;}

    public void SetDoubleBarre(){
    this.doublebarre=true;}

    public void NoDoubleBarre(){
    this.doublebarre=false;}

    public void NoLaser(){
    this.laser=false;}

    public void SetLaser(){
    this.laser=true;}

    public void SetColler(){
    this.coller=true;}

    public void Bonus_to_Option(Bonus bonus){
    if(bonus.Getcoller()){this.SetColler();}
    if(bonus.Getmultiballe()){this.SetMultiballe();}
    if(bonus.Getlaser()){this.SetLaser();}
    if(bonus.Getvie()){this.addVie();}
    if(bonus.Getmort()){this.supVie();}
    if(bonus.Getinvincible()){this.SetInvincible();}
    if(bonus.Getdoublebarre()){this.SetDoubleBarre();}
    }

    public void Dessiner(Graphics g){
    g.setColor(Color.black);
    this.cadre.Dessiner(g);
    g.drawString("Score : "+this.Score*100,this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/5),this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/20));
    g.drawString("Vie : "+this.vie,this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/5),this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/10));
    g.drawString("Coller : "+this.coller,this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/5),this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/5));
    g.drawString("Invincible : "+this.invincible,this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/5),20+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/5));
    g.drawString("Laser : "+this.laser,this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/5),40+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/5));
    g.drawString("doubleBarre : "+this.doublebarre,this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/5),60+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/5));

    g.setColor(Color.magenta);
    g.drawString("Invincible  ",this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/4),this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2));
    g.fillOval(this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/6), this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2)-RAYON,RAYON,RAYON);

    g.setColor(Color.red);
    g.drawString("Laser  ",this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/4),20+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2));
    g.fillOval(this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/6), 20+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2)-RAYON,RAYON,RAYON);

    g.setColor(Color.orange);
    g.drawString("Multiballe  ",this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/4),40+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2));
    g.fillOval(this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/6), 40+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2)-RAYON,RAYON,RAYON);

    g.setColor(Color.pink);
    g.drawString("Vie  ",this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/4),60+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2));
    g.fillOval(this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/6), 60+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2)-RAYON,RAYON,RAYON);

    g.setColor(Color.blue);
    g.drawString("BoubleBarre  ",this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/4),80+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2));
    g.fillOval(this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/6), 80+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2)-RAYON,RAYON,RAYON);

    g.setColor(Color.green);
    g.drawString("Coller  ",this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/4),100+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2));
    g.fillOval(this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/6), 100+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2)-RAYON,RAYON,RAYON);

    g.setColor(Color.black);
    g.drawString("Mort  ",this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/4),120+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2));
    g.fillOval(this.cadre.GetBordGauche()+(this.cadre.GetLargeur()/6), 120+this.cadre.GetBordHaut()+(this.cadre.GetHauteur()/2)-RAYON,RAYON,RAYON);

    }

    public void EnDessiner(Graphics g){
    g.setColor(Color.white);
    g.fillRect(this.cadre.GetBordGauche()+1,this.cadre.GetBordHaut()+1,this.cadre.GetLargeur()-2,this.cadre.GetHauteur()/3);

    }
    }

    class ListeBonus {
    private Chainon premier;

    private class Chainon{
    private Bonus data;
    private Chainon suivant;
    }

    public ListeBonus(){
    premier=null;}

    public boolean estVide(){
    return (premier==null);}

    public Bonus getPremier(){
    if(estVide()) {
        throw new NoSuchElementException();
    }
        return premier.data;
    }

    public void insererEnTete(Bonus data){
    Chainon nvChn=new Chainon();
    nvChn.data=data;
    nvChn.suivant=premier;
    premier=nvChn;}

    public void insererEnQueue(Bonus data){
    Chainon curseur=new Chainon();
    Chainon nvChn=new Chainon();
    nvChn.data=data;
    curseur=premier;
    if(estVide()){this.insererEnTete(data);}
    else{
    while(curseur.suivant!=null){
    curseur=curseur.suivant;}
    curseur.suivant=nvChn;}
    }

    public void retrancherEnTete(){
    if(estVide()) {
        throw new NoSuchElementException();
    } else{premier=premier.suivant;}}

    public void supprimerElt(Chainon bonus){
    Chainon curseur=new Chainon();
    curseur=premier;
    if(curseur==null) {
        throw new NoSuchElementException();
    }

    if(curseur.data.Getx()==bonus.data.Getx()){this.retrancherEnTete();}
    else{
    while(curseur.suivant.data.Getx()!=bonus.data.Getx() && curseur.suivant.data.Gety()!=bonus.data.Gety() && curseur.suivant!=null){
    curseur=curseur.suivant;}
    curseur.suivant=curseur.suivant.suivant;}
    }

    public void Fall(Barre tige){
    Chainon curseur=new Chainon();
    curseur=premier;
    while(curseur!=null){
    if(curseur.data.TestFall(tige)){this.supprimerElt(curseur);}
    curseur=curseur.suivant;
    }
    }

    public Bonus TestBarre(Barre tige){
    Bonus pasdebonus=new Bonus(0,0);
    Chainon curseur=new Chainon();
    curseur=premier;
    while(curseur!=null){
    if(curseur.data.TestBarre(tige)){this.supprimerElt(curseur);return curseur.data;}
    curseur=curseur.suivant;
    }
    return pasdebonus;
    }

    public void Move(){
    Chainon curseur=new Chainon();
    curseur=premier;
     while(curseur!=null){
    curseur.data.Move();
    curseur=curseur.suivant;
    }
    }

    public void Dessiner(Graphics g){
    Chainon curseur=new Chainon();
    curseur=premier;
     while(curseur!=null){
    curseur.data.Dessiner(g);
    curseur=curseur.suivant;
    }
    }

    public void EnDessiner(Graphics g){
    Chainon curseur=new Chainon();
    curseur=premier;
     while(curseur!=null){
    curseur.data.EnDessiner(g);
    curseur=curseur.suivant;
    }
    }

    }

    class Bonus{
    public static int RAYON=10;
    public static int RAPIDITE=5;
    public static int LARGEUR=500;
    public static int HAUTEUR=600;

    private int x;
    private int y;
    private int r;
    private boolean invincible;
    private boolean doublebarre;
    private boolean multiballe;
    private boolean vie;
    private boolean mort;
    private boolean laser;
    private boolean coller;

    public Bonus(int x,int y){
    this.x=x;
    this.y=y;
    this.r=RAYON;
    this.invincible=false;
    this.doublebarre=false;
    this.multiballe=false;
    this.vie=false;
    this.mort=false;
    this.laser=false;
    this.coller=false;
    }

    public Bonus(Brique brique){
    this.x=brique.Getx()+(brique.GetLargeur()/2);
    this.y=brique.Gety()+(brique.GetHauteur()/2);
    this.r=RAYON;
    this.invincible=false;
    this.doublebarre=false;
    this.multiballe=false;
    this.vie=false;
    this.mort=false;
    this.laser=false;
    this.coller=false;
    }

    public void Init(){
    this.invincible=false;
    this.doublebarre=false;
    this.multiballe=false;
    this.vie=false;
    this.mort=false;
    this.laser=false;
    this.coller=false;
    }

    public int Getx(){
    return this.x;}

    public int Gety(){
    return this.y;}

    public int GetRayon(){
    return this.r;}

    public boolean Getinvincible(){
    return this.invincible;}

    public boolean Getdoublebarre(){
    return this.doublebarre;}

    public boolean Getmultiballe(){
    return this.multiballe;}

    public boolean Getvie(){
    return this.vie;}

    public boolean Getmort(){
    return this.mort;}

    public boolean Getlaser(){
    return this.laser;}

    public boolean Getcoller(){
    return this.coller;}

    public void Setinvincible(){
    this.invincible=!this.invincible;}

    public void Setdoublebarre(){
    this.doublebarre=!this.doublebarre;}

    public void Setmultiballe(){
    this.multiballe=!this.multiballe;}

    public void Setvie(){
    this.vie=!this.vie;}

    public void Setlaser(){
    this.laser=!this.laser;}

    public void Setcoller(){
    this.coller=!this.coller;}

    public void Setx(int x){
    this.x=x;}

    public void Sety(int y){
    this.y=y;}

    public void Move(){
    this.y += (this.r/5);}

    public void Move(int y){
    this.y += y;}

    public void Move(int x,int y){
    this.x += x;
    this.y += y;}

    public boolean TestFall(Barre tige){
    if(this.Gety()>=tige.Gety()){return true;}
    return false;
    }

    public boolean TestBarre(Barre tige){
    if(this.Gety()+this.r>=tige.GetBordHaut() && this.Getx()+(this.r/2)>=(tige.GetBordGauche()) && this.Getx()+(this.r/2)<=(tige.GetBordDroit()))
    {return true;}
    return false;
    }

    public void Random(){
    int tmp;
    Random rnd = new Random( );
    tmp=rnd.nextInt()%7;

    switch (tmp){
    case 0:
         this.vie=true;
                break;
    case 1:
         this.invincible=true;
                break;
    case 2:
         this.doublebarre=true;
                break;
    case 3:
         this.multiballe=true;
                break;

    case 4:
         this.mort=true;
                break;
    case 5:
         this.coller=true;
                break;
    case 6:
         this.laser=true;
                break;

    default:;
    }
    }

    public void Dessiner(Graphics g){
    if(this.vie){g.setColor(Color.pink);}
    if(this.mort){g.setColor(Color.black);}
    if(this.multiballe){g.setColor(Color.orange);}
    if(this.coller){g.setColor(Color.green);}
    if(this.laser){g.setColor(Color.red);}
    if(this.doublebarre){g.setColor(Color.blue);}
    if(this.invincible){g.setColor(Color.magenta);}
    if(this.vie || this.mort || this.multiballe || this.coller || this.laser || this.doublebarre || this.invincible)
    {g.fillOval(this.x, this.y,this.r,this.r);}
    }

    public void EnDessiner(Graphics g){
    g.setColor(Color.white);
    if(this.vie || this.mort || this.multiballe || this.coller || this.laser || this.doublebarre || this.invincible){
    g.fillOval(this.x, this.y,this.r,this.r);}
    }
    }

    class Brique{
    public static int RAYON=10;
    public static int RAPIDITE=5;
    public static int LARGEUR=500;
    public static int HAUTEUR=600;

    private  int x;
    private  int y;
    private  boolean existe;
    private  int Largeur;
    private  int Hauteur;

    public Brique(int x,int y,int largeur,int hauteur,boolean bool){
           this.x=x;
           this.y=y;
           this.Largeur=largeur;
           this.Hauteur=hauteur;
           this.existe=bool;
           }

    public Brique(int x,int y,int largeur,int hauteur){
           this.x=x;
           this.y=y;
           this.Largeur=largeur;
           this.Hauteur=hauteur;
           this.existe=true;
           }

    public Brique(int x,int y){
           this.x=x;
           this.y=y;
           this.Largeur=(HAUTEUR/20)-1;
           this.Hauteur=(LARGEUR/20)-1;
           this.existe=true;
    }

    public Brique(int x,int y,Level level){
           this.x=x;
           this.y=y;
           this.Largeur=(LARGEUR/level.GetMatrice()[0].length)-1;
           this.Hauteur=(HAUTEUR/level.GetMatrice().length)-1;
           this.existe=true;
    }

    public void Casser(){
           this.existe=false;
    }

    public int Getx(){
    return this.x;}

    public int Gety(){
    return this.y;}

    public int GetLargeur(){
    return this.Largeur;}

    public int GetHauteur(){
    return this.Hauteur;}

    public boolean GetExiste(){
    return this.existe;}

    public int GetBordGauche(){
    return this.x;}

    public int GetBordDroit(){
    return this.x+this.Largeur;}

    public int GetBordHaut(){
    return this.y;}

    public int GetBordBas(){
    return this.y+this.Hauteur;
    }

    public void Dessiner(Graphics g){
    g.setColor(Color.red);
    g.fillRect(this.x,this.y,this.Largeur,this.Hauteur);}

    public void EnDessiner(Graphics g){
    g.setColor(Color.white);
    g.fillRect(this.x,this.y,this.Largeur,this.Hauteur);}

    }

    class Level{
    public static int RAYON=10;
    public static int RAPIDITE=5;
    public static int LARGEUR=500;
    public static int HAUTEUR=600;

    private Brique[][] matrice;
    private int x;
    private int y;

    public Level(int x,int y,int u,int v){
           this.matrice=new Brique[x][y];
           this.x=u;
           this.y=v;
           }

    public Level(int n,int x,int y){
           this.matrice=new Brique[n][n];
           this.x=x;
           this.y=y;
           }

    public Level(Brique[][] tableau,int x,int y){
           this.matrice=tableau;
           this.x=x;
           this.y=y;
           }

    public Level(int x,int y){
           this.matrice=new Brique[10][10];
           this.x=x;
           this.y=y;
           }

    public int Getx(){
    return this.x;}

    public int Gety(){
    return this.x;}

    public int GetBordGauche(){
    return this.x;}

    public int GetBordDroit(){
    return this.x+(this.matrice[0].length*this.matrice[0][0].GetLargeur());}

    public int GetBordBas(){
    return this.y+(this.matrice.length*this.matrice[0][0].GetHauteur());}

    public int GetBordHaut(){
    return this.y;}

    public boolean LevelFini(){
    boolean tmp=true;
    for(int i=0;i<this.matrice.length;i++){
        for(int j=0;j<this.matrice[i].length;j++){
    tmp=(tmp && !this.matrice[i][j].GetExiste());}}
    return tmp;
    }

    public Level Level1(Level level1){
    int x1,y1,nbrique;
    nbrique=(level1.matrice[0].length)*(level1.matrice.length);
    level1.matrice[0][0]=new Brique(level1.x,level1.y,level1);
    for(int i=0;i<level1.matrice.length;i++){
        for(int j=0;j<level1.matrice[i].length;j++){
    x1=(level1.matrice[0][0].GetLargeur()+1)*j;
    y1=(level1.matrice[0][0].GetHauteur()+1)*i;
    this.matrice[i][j]=new Brique(x1+level1.x,y1+level1.y,level1);
        }
    }
    return level1;
    }

    public void Level2(){
    int x1,y1,tmp,tmp1;
    this.matrice[0][0]=new Brique(this.x,this.y);
    tmp=this.matrice[0][0].GetLargeur();
    tmp1=this.matrice[0][0].GetHauteur();
    for(int i=0;i<this.matrice.length;i++){
        for(int j=0;j<this.matrice[i].length;j++){
    x1=(tmp+1)*j;
    y1=(tmp1+1)*i;
    this.matrice[i][j]=new Brique((this.x+x1),(this.y+y1));
        }
    }
    }

    public Brique[][] GetMatrice(){
    return this.matrice;
    }

    public boolean TestSiBrique(int x, int y){
    return this.matrice[x][y].GetExiste();
    }

    public void Dessiner(Graphics g){
    for(int i=0;i<this.matrice.length;i++){
        for(int j=0;j<this.matrice[i].length;j++){
    if(this.matrice[i][j].GetExiste()){this.matrice[i][j].Dessiner(g);} else{this.matrice[i][j].EnDessiner(g);}
        }
    }
    }

    }

    class Boule{

    public static int RAYON=10;
    public static int RAPIDITE=5;
    public static int LARGEUR=500;
    public static int HAUTEUR=600;

    private int x;
    private int y;
    private int r;
    private int dx;
    private int dy;

           public Boule(int x,int y){
           this.x=x;
           this.y=HAUTEUR-y;
           this.r=RAYON;
           this.dx=0;
           this.dy=0;
           }

           public Boule(Cadre tableau){
           this.x=(tableau.GetBordDroit()-tableau.GetBordGauche())/2;
           this.y=(tableau.GetBordBas());
           this.r=RAYON;
           this.dx=0;
           this.dy=0;
           }

           public Boule(int x,int y,int dx,int dy){
           this.x=x;
           this.y=HAUTEUR-y;
           this.r=RAYON;
           this.dx=dx;
           this.dy=-dy;
           }

           public Boule(Cadre tableau,int dx,int dy){
           this.x=(tableau.GetBordDroit()-tableau.GetBordGauche())/2;
           this.y=(tableau.GetBordHaut());
           this.r=RAYON;
           this.dx=dx;
           this.dy=-dy;
           }

           public Boule(Barre tige,int dx,int dy){
           this.x=tige.GetBordGauche()+(tige.GetBordDroit()-tige.GetBordGauche()-this.r)/2;
           this.r=RAYON;
           this.y=(tige.Gety()-(this.r+1));
           this.dx=dx;
           this.dy=-dy;
           }

           public int Getx(){
           return this.x;}

           public int Gety(){
           return this.y;}

           public int Getdx(){
           return this.dx;}

           public int Getdy(){
           return this.dy;}

           public void Setdx(int dx){
           this.dx=dx;}

           public void Setdy(int dy){
           this.dy=dy;}

           public int PrecedentGetx(){
           return this.x-this.dx;}

           public int PrecedentGety(){
           return this.y-this.dy;}

           public void Move(){
            this.x += this.dx;
            this.y += this.dy; }

           public void Move(int dx,int dy){
            this.x += dx;
            this.y += dy;
            }

           public void RebondCote(){
           this.dx=-this.dx;}

           public void RebondHauteur(){
           this.dy=-this.dy;}

           public void RebondBarre(int dx1,int dy1){
           this.dx=dx1;
           this.dy=-dy1;}

           public void Dessiner(Graphics g){
            g.setColor(Color.black);
            g.fillOval(this.x, this.y,this.r,this.r);}

           public void EnDessiner(Graphics g){
           g.setColor(Color.white);
           g.fillOval(this.x, this.y,this.r,this.r);}

           public Boule TestCadre(Cadre tableau){
             if(this.Getx()<=tableau.GetBordGauche())
           {this.Move(tableau.GetBordGauche()-this.x,this.dy*(tableau.GetBordGauche()-this.x)/this.dx);this.RebondCote();}
           if(this.Gety()<=tableau.GetBordHaut())
           {this.Move((tableau.GetBordHaut()-this.y)*this.dx/this.dy,tableau.GetBordHaut()-this.y); this.RebondHauteur();}
           if(this.Getx()+this.r>=tableau.GetBordDroit())
           {this.Move(tableau.GetBordDroit()-this.x-this.r,this.dy*(tableau.GetBordDroit()-this.x)/this.dx);this.RebondCote();}
           return this;
           }

           public boolean TestBarre(Barre tige){
           if(this.Gety()+this.r>=tige.GetBordHaut() && this.Getx()+(this.r/2)>=(tige.GetBordGauche()) && this.Getx()+(this.r/2)<=(tige.GetBordDroit()))
           {int tmp=tige.CoefficientRebond(this.Getx());this.RebondBarre(tmp*Math.abs(this.dx)/this.dx,this.dy);return true;}
           return false;
           }

           public Brique TestBriqueInvincible(Level level,int score){
           int j=0,i=0,k=0,l=0;
           Brique brique=null;
           boolean Casse=false;
           if(this.Getx()+this.r>=level.GetBordGauche() && this.Getx()-this.r<=level.GetBordDroit() && this.Gety()-this.r<=level.GetBordBas() && this.Gety()+this.r>=level.GetBordHaut()){
           j= (this.Getx()-level.GetBordGauche())/(level.GetMatrice()[0][0].GetLargeur()+1);
           i= (this.Gety()-level.GetBordHaut())/(level.GetMatrice()[0][0].GetHauteur()+1);
           k= (this.Getx()+this.dx-level.GetBordGauche())/(level.GetMatrice()[0][0].GetLargeur()+1);
           l= (this.Gety()+this.dy-level.GetBordHaut())/(level.GetMatrice()[0][0].GetHauteur()+1);

           if(i<level.GetMatrice().length){
           if(j<level.GetMatrice()[i].length){

           if(level.GetMatrice()[i][j].GetExiste()){

           level.GetMatrice()[i][j].Casser();
           ++score;
           Casse=true;
           brique=level.GetMatrice()[i][j];
            if(this.Gety()+this.r>level.GetMatrice()[i][j].GetBordHaut() && this.Gety()<level.GetMatrice()[i][j].GetBordBas()){

            }

            if(this.Getx()<level.GetMatrice()[i][j].GetBordDroit() && this.Getx()+this.r>level.GetMatrice()[i][j].GetBordGauche()){

            }

           }
            if(l<level.GetMatrice().length){
            if(level.GetMatrice()[l][j].GetExiste()){

            if(this.Getx()<level.GetMatrice()[l][j].GetBordDroit() && this.Getx()+this.r>level.GetMatrice()[l][j].GetBordGauche()){
            level.GetMatrice()[l][j].Casser();
            ++score;
            Casse=true;
            brique=level.GetMatrice()[l][j];
            }
            }

            }

           if(i<level.GetMatrice().length){
           if(k<level.GetMatrice()[i].length){
            if(level.GetMatrice()[i][k].GetExiste()){

            if(this.Gety()>=level.GetMatrice()[i][k].GetBordHaut() && this.Gety()+1<=level.GetMatrice()[i][k].GetBordBas()){
            level.GetMatrice()[i][k].Casser();
            ++score;
            Casse=true;
            brique=level.GetMatrice()[i][k];}
            }
           }
           }
           }
           }
           }
           return brique;
           }

           public Brique TestBrique(Level level,int score){
           int j=0,i=0,k=0,l=0;
           Brique brique=null;
           boolean Casse=false;
           if(this.Getx()+this.r>=level.GetBordGauche() && this.Getx()-this.r<=level.GetBordDroit() && this.Gety()-this.r<=level.GetBordBas() && this.Gety()+this.r>=level.GetBordHaut()){
           j= (this.Getx()-level.GetBordGauche())/(level.GetMatrice()[0][0].GetLargeur()+1);
           i= (this.Gety()-level.GetBordHaut())/(level.GetMatrice()[0][0].GetHauteur()+1);
           k= (this.Getx()+this.dx-level.GetBordGauche())/(level.GetMatrice()[0][0].GetLargeur()+1);
           l= (this.Gety()+this.dy-level.GetBordHaut())/(level.GetMatrice()[0][0].GetHauteur()+1);

           if(i<level.GetMatrice().length){
           if(j<level.GetMatrice()[i].length){

           if(level.GetMatrice()[i][j].GetExiste()){

           level.GetMatrice()[i][j].Casser();
           ++score;
           Casse=true;
           brique=level.GetMatrice()[i][j];
            if(this.Gety()+this.r>level.GetMatrice()[i][j].GetBordHaut() && this.Gety()<level.GetMatrice()[i][j].GetBordBas()){
            this.RebondCote();
            }

            if(this.Getx()<level.GetMatrice()[i][j].GetBordDroit() && this.Getx()+this.r>level.GetMatrice()[i][j].GetBordGauche()){
            this.RebondHauteur();
            }

           }
            if(l<level.GetMatrice().length){
            if(level.GetMatrice()[l][j].GetExiste()){

            if(this.Getx()<level.GetMatrice()[l][j].GetBordDroit() && this.Getx()+this.r>level.GetMatrice()[l][j].GetBordGauche()){
            level.GetMatrice()[l][j].Casser();
            ++score;
            Casse=true;
            brique=level.GetMatrice()[l][j];
            this.RebondHauteur();}
            }

            if(k<level.GetMatrice()[l].length){

            if(level.GetMatrice()[l][k].GetExiste()){

            if(this.Getx()<level.GetMatrice()[l][k].GetBordDroit() && this.Getx()>level.GetMatrice()[l][k].GetBordGauche()){
            level.GetMatrice()[l][k].Casser();++score;
            this.RebondHauteur();}

            if(this.Gety()>level.GetMatrice()[l][k].GetBordHaut() && this.Gety()<level.GetMatrice()[l][k].GetBordBas()){
            level.GetMatrice()[l][k].Casser();++score;
            this.RebondCote();}
            }

            }

            }
            

           if(i<level.GetMatrice().length){
           if(k<level.GetMatrice()[i].length){
            if(level.GetMatrice()[i][k].GetExiste()){

            if(this.Gety()>=level.GetMatrice()[i][k].GetBordHaut() && this.Gety()+1<=level.GetMatrice()[i][k].GetBordBas()){
            this.RebondCote();
            level.GetMatrice()[i][k].Casser();
            ++score;
            Casse=true;
            brique=level.GetMatrice()[i][k];}
            }
           }
           }
           }
           }
           }
           return brique;
           }

           public boolean TestFall(Barre tige){
           if(this.Gety()>=tige.Gety()){return true;}
           return false;
           }

   }

    class Cadre{
    public static int RAYON=10;
    public static int RAPIDITE=5;
    public static int LARGEUR=500;
    public static int HAUTEUR=600;

    private int largeur;
    private int hauteur;
    private int x;
    private int y;

           public Cadre(int x,int y,int largeur,int hauteur){
           this.largeur=largeur;
           this.hauteur=hauteur;
           this.x=x;
           this.y=y;
           }

           public Cadre(int x,int y){
           this.largeur=LARGEUR;
           this.hauteur=HAUTEUR;
           this.x=x;
           this.y=y;
           }

           public Cadre(){
           this.largeur=LARGEUR;
           this.hauteur=HAUTEUR;
           this.x=0;
           this.y=0;
           }

           public int Getx(){
           return this.x;
           }

           public int Gety(){
           return this.y;
           }

           public int GetBordGauche(){
           return this.x;
           }

           public int GetBordDroit(){
           return (this.x+this.largeur);
           }

           public int GetBordHaut(){
           return this.y;
           }

           public int GetBordBas(){
           return (this.y+this.hauteur);
           }

           public int GetHauteur(){
           return this.hauteur;}

           public int GetLargeur(){
           return this.largeur;}

           public void Dessiner(Graphics g){
           g.setColor(Color.black);
           g.drawLine(this.GetBordGauche(),this.GetBordHaut(),this.GetBordDroit(),this.GetBordHaut());
           g.drawLine(this.GetBordGauche(),this.GetBordBas(),this.GetBordGauche(),this.GetBordHaut());
           g.drawLine(this.GetBordDroit(),this.GetBordHaut(),this.GetBordDroit(),this.GetBordBas());
           }

   }

class Barre {
 public static int RAYON=10;
 public static int RAPIDITE=5;
 public static int LARGEUR=500;
 public static int HAUTEUR=600;

    private int Largeur;
    private int Hauteur;
    private int x;
    private int y;

    public Barre(int x,int y,int hauteur,int largeur){
    this.x=x;
    this.y=y;
    this.Hauteur=hauteur;
    this.Largeur=largeur;
    }

    public Barre(Cadre tab,int hauteur,int largeur){
    this.x=(tab.GetBordDroit()-tab.GetBordGauche()-largeur)/2;
    this.y=tab.GetBordBas()+(hauteur/2);
    this.Hauteur=hauteur;
    this.Largeur=largeur;
    }

    public Barre(Cadre tab,int hauteur){
    this.y=tab.GetBordBas()+(hauteur/2);
    this.Hauteur=hauteur;
    this.Largeur=(tab.GetBordDroit()-tab.GetBordGauche())/8;
    this.x=(tab.GetBordDroit()-tab.GetBordGauche()-this.Largeur)/2;
    }

    public Barre(Cadre tab){
    this.Hauteur=(tab.GetBordBas()-tab.GetBordHaut())/90;
    this.Largeur=(tab.GetBordDroit()-tab.GetBordGauche())/8;
    this.x=(tab.GetBordDroit()-tab.GetBordGauche()-this.Largeur)/2;
    this.y=tab.GetBordBas()+(this.Hauteur/2);
    }

    public Barre(int x,int y,Cadre tab){
    this.Hauteur=(tab.GetBordBas()-tab.GetBordHaut())/90;
    this.Largeur=(tab.GetBordDroit()-tab.GetBordGauche())/8;
    this.x=x-(this.Largeur/2);;
    this.y=y+(this.Hauteur/2);;
    }

    public Barre(int x,Cadre tab){
    this.Hauteur=(tab.GetBordBas()-tab.GetBordHaut())/90;
    this.Largeur=(tab.GetBordDroit()-tab.GetBordGauche())/8;
    this.x=x-(this.Largeur/2);
    this.y=tab.GetBordBas()+(this.Hauteur/2);
    }

    public Barre(){
    this.Hauteur=HAUTEUR/90;
    this.Largeur=LARGEUR/8;
    this.x=(LARGEUR-this.Largeur)/2;
    this.y=HAUTEUR+(this.Hauteur*2);
    }

    public void Givex(int x){
    this.x=x;
    }

    public int Getx(){
    return this.x;
    }

    public int GetLargeur(){
    return this.Largeur;}

    public int GetHauteur(){
    return this.Hauteur;}

    public int Gety(){
    return this.y;
    }

    public int GetBordGauche(){
    return this.x;
    }

    public int GetBordDroit(){
    return (this.x+this.Largeur);
    }

    public int GetBordHaut(){
    return this.y;
    }

    public int GetBordBas(){
    return (this.y+this.Hauteur);
    }

    public int CoefficientRebond(int x){
    int coefbord=((this.Largeur)/10);
    int coefdemibord=((this.Largeur)/5);
    if(x<=(this.Getx()+coefbord) || x>=((this.Getx()+(coefbord*9)))) {return -3;}
    if(x<=(this.Getx()+coefdemibord) || x>=(this.Getx()+(coefdemibord*4))) {return 2;}
    return 1;
    }

    public void EnDessiner(Graphics g){
    g.setColor(Color.white);
    g.fillRect(this.x,this.y,this.Largeur,this.Hauteur);
    }

    public void Dessiner(Graphics g){
    g.setColor(Color.black);
    g.fillRect(this.x,this.y,this.Largeur,this.Hauteur);
    }
}