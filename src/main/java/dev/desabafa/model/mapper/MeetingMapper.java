package dev.desabafa.model.mapper;

import dev.desabafa.model.dto.response.user.calendar.MeetingResponse;
import dev.desabafa.model.entity.Meeting;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MeetingMapper {

    @Mappings({
        @Mapping(source = "id",                           target = "meetingId"),
        @Mapping(source = "createdTime",                  target = "createdTime"),
        @Mapping(source = "updatedTime",                  target = "updatedTime"),
        @Mapping(source = "details.date",                 target = "date"),
        @Mapping(source = "details.time",                 target = "time"),
        @Mapping(source = "details.duration",             target = "duration"),
        @Mapping(source = "details.description",          target = "description"),
        @Mapping(source = "details.status",               target = "status"),
        @Mapping(source = "details.predictionMessage",    target = "predictionMessage"),
        @Mapping(source = "details.transcriptionMessage", target = "transcriptionMessage"),

        @Mapping(target = "userProfile", ignore = true)
    })
    Meeting toEntity(MeetingResponse dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
        @Mapping(source = "details.date",                 target = "date"),
        @Mapping(source = "details.time",                 target = "time"),
        @Mapping(source = "details.duration",             target = "duration"),
        @Mapping(source = "details.description",          target = "description"),
        @Mapping(source = "details.status",               target = "status"),
        @Mapping(source = "details.predictionMessage",    target = "predictionMessage"),
        @Mapping(source = "details.transcriptionMessage", target = "transcriptionMessage")
    })
    void updateFromDto(MeetingResponse dto, @MappingTarget Meeting entity);


    default OffsetDateTime map(LocalDateTime ldt) {
        return ldt == null ? null : ldt.atOffset(ZoneOffset.UTC);
    }

    default OffsetDateTime map(LocalDate ld) {
        return ld == null ? null : ld.atStartOfDay().atOffset(ZoneOffset.UTC);
    }

}
