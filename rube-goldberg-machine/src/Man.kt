class Man(var firstName: String = MaleNames.PAVLO.getName(), var lastName: String = "LastN") : Human() {
    // 17. Inheritance of the class () 18. default values. 19. Sugar constructors.

    override fun write() : String { // 20. Override
        return "$firstName $lastName"
    }
}
