package com.fieldbooking.server.service.implementation;

import com.fieldbooking.server.model.entity.Reservation;
import com.fieldbooking.server.repository.ReservationRepository;
import com.fieldbooking.server.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements AbstractService<Reservation> {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> findByUserId(Long id) {
        return reservationRepository.findByUserId(id);
    }
}
