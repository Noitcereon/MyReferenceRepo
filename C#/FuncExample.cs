using System;


public class FuncExample
{
    public static void Main(string[] args)
    {
        // The point is that ReptileEgg can create both a FireDragon and a Crocodile (and other IReptiles)
        // Without changing its internal workings.
        var fireDragon = new FireDragon();
        var egg = fireDragon.Lay();
        var childFireDragon = egg.Hatch();
        var crocodile = new Crocodile();
        var crocEgg = crocodile.Lay();
        crocEgg.Hatch();
        Console.ReadKey();
    }

    #region Classes
    public class FireDragon : IReptile
    {
        public FireDragon()
        {
        }

        public ReptileEgg Lay() => new ReptileEgg(CreateFiredragon);

        public FireDragon CreateFiredragon()
        {
            return new FireDragon();
        }
        public override String ToString()
        {
            return nameof(FireDragon);
        }
    }

    public class Crocodile : IReptile
    {
        public ReptileEgg Lay() => new ReptileEgg(() => new Crocodile());

        public override String ToString()
        {
            return nameof(Crocodile);
        }
    }


    public class ReptileEgg
    {
        private IReptile _reptile;
        public ReptileEgg(Func<IReptile> createReptile)
        {
            _reptile = createReptile();
        }

        public IReptile Hatch()
        {
            Console.WriteLine("ReptileEgg hatches a " + _reptile.ToString() + "!");
            return _reptile;
        }
    }
    #endregion

    public interface IReptile
    {
        ReptileEgg Lay();
    }
}
