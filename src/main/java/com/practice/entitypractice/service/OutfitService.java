package com.practice.entitypractice.service;

import com.practice.entitypractice.data.person.Outfit;
import com.practice.entitypractice.repository.OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutfitService {
    @Autowired
    OutfitRepository outfitRepository;

    public void save(Outfit outfit) {
        outfitRepository.save(outfit);
    }

    public Outfit removeOutfit(Long id) {
        return outfitRepository.findById(id).orElse(null);
    }
}
