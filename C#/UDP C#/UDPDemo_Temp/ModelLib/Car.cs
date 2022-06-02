using System;
using System.Collections.Generic;
using System.Text;

namespace ModelLib
{
    public class Car
    {
        private string _model;
        private string _color;
        private string _registrationNumber;

        public Car()
        {
        }

        public Car(string model, string color, string registrationNumber)
        {
            _model = model;
            _color = color;
            _registrationNumber = registrationNumber;
        }

        public string Model
        {
            get => _model;
            set => _model = value;
        }

        public string Color
        {
            get => _color;
            set => _color = value;
        }

        public string RegistrationNumber
        {
            get => _registrationNumber;
            set => _registrationNumber = value;
        }

        public override string ToString()
        {
            return $"CarModel: {Model}, {Color}, {RegistrationNumber}";
        }
    }
}
