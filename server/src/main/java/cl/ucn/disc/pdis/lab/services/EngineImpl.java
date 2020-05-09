/*
 * Copyright (c) 2020 Diego Urrutia-Astorga. http://durrutia.cl.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package cl.ucn.disc.pdis.lab.services;

import cl.ucn.disc.pdis.lab.zeroice.model.Engine;
import com.zeroc.Ice.Current;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The implementation of {@link cl.ucn.disc.pdis.lab.zeroice.model.Engine}.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class EngineImpl implements Engine {

    /**
     * @see Engine#getDate(Current)
     */
    @Override
    public String getDate(Current current) {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    @Override
    public String getDigitoVerificador(String rut, Current current) {
        // Si esta vacio
        if (rut.length() == 0) {
            return "Rut null";
        }

        int result = 0;
        for (int i = rut.length() - 1; i >= 0; i--) {
            int dig = i;
            // Calculo para el digito
            result += dig * (((rut.length() - (1 + i)) % 6) + 2);

            if(Character.isLetter(rut.charAt(i))) {
                return null;
            }
        }
        // Calculo del modulo
        int modul = 11 - (result % 11);

        String dv = String.valueOf(11 - modul);

        // Retorna 0 o K dependiendo del resultado
        if(dv.equals("11")) return "0";
        else if(dv.equals("10")) return "K";

        return dv;

    }

}
