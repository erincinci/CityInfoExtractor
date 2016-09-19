package com.erincinci.exporters;

/**
 * Exporter Interface
 * Created by erincinci on 19.09.2016.
 */
public interface IExporter<INPUT, RETURN> {
    RETURN export(INPUT arg);
}
