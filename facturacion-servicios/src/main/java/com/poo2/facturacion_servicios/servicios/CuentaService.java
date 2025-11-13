public Cuenta cambiarEstadoCuenta(Long id, EstadoCuenta nuevoEstado) {
    Cuenta cuenta = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    cuenta.setEstado(nuevoEstado);
    return repo.save(cuenta);
}
