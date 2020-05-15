import de.htwg.se.durak.model.CardStack
import de.htwg.se.durak.model.Card

val s = new CardStack()
s.addCard(new Card(2,4))
s.addCard(new Card(7,3))
s.addCard(new Card(3,2))
s.addCard(new Card(5,3))
s.addCard(new Card(9,1))
s.addCard(new Card(11,2))
s.getAllCards()