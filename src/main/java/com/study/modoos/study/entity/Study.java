package com.study.modoos.study.entity;

import com.study.modoos.member.entity.Campus;
import com.study.modoos.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "study")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "leader_id", referencedColumnName = "id")
    private Member leader;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @ColumnDefault("3")
    @Min(3)
    @Column(name = "recruits_count")
    private int recruits_count;

    @ColumnDefault("1")
    @Min(1)
    @Column(name = "participants_count")
    private int participants_count;

    @ColumnDefault("0")
    @Column(name = "status")
    private int status;

    @Column(nullable = false)
    private LocalDate recruit_deadline;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    @Column(nullable = false, updatable = false)
    private LocalDate expected_start_at;

    @Column(nullable = false, updatable = false)
    private LocalDate expected_end_at;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Campus campus;

    @Column(name = "contact")
    private String contact;

    @Column(name = "link")
    private String link;

    @Column(nullable = false, length = 2000)
    private String rule_content;

    @ColumnDefault("0")
    @Column(name = "absent")
    private int absent;

    @ColumnDefault("0")
    @Column(name = "late")
    private int late;

    @ColumnDefault("0")
    @Column(name = "outs")
    private int out;

    @Column(updatable = false)
    private LocalDate start_at;

    @Column(updatable = false)
    private LocalDate end_at;

    @ColumnDefault("0")
    @Column(name = "period")
    private int period;

    @ColumnDefault("0")
    @Column(name = "total_turn")
    private int total_turn;

    @Builder
    public Study(Member leader, String title, String description, int recruits_count,
                 LocalDate recruit_deadline, String channel, LocalDate expected_start_at,
                 LocalDate expected_end_at, String category, String campus, String contact,
                 String link, String rule_content, int absent, int late, int out) {
        this.leader = leader;
        this.title = title;
        this.description = description;
        this.recruits_count = recruits_count;
        this.participants_count = 1;
        this.recruit_deadline = recruit_deadline;
        this.channel = Channel.valueOf(channel);
        this.expected_start_at = expected_start_at;
        this.expected_end_at = expected_end_at;
        this.category = Category.valueOf(category);
        this.campus = Campus.valueOf(campus);
        this.contact = contact;
        this.link = link;
        this.rule_content = rule_content;
        this.absent = absent;
        this.late = late;
        this.out = out;
    }

    public void update(String campus, String channel, String category, LocalDate expected_start_at,
                       LocalDate expected_end_at, String contact, String link, String title, String description,
                       int absent, int late, int out, String rule_content) {
        this.title = title;
        this.description = description;
        this.channel = Channel.valueOf(channel);
        this.expected_start_at = expected_start_at;
        this.expected_end_at = expected_end_at;
        this.category = Category.valueOf(category);
        this.campus = Campus.valueOf(campus);
        this.contact = contact;
        this.link = link;
        this.rule_content = rule_content;
        this.absent = absent;
        this.late = late;
        this.out = out;
    }

    /*
     * 나중에 여기에 스터디 생성 시 진짜 시작일, 종료일, 회차, 기간 update하는 로직 넣기(생성자 새로 만들지 x)
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        else if (!(obj instanceof Study)) return false;
        Study study = (Study) obj;
        return Objects.equals(id, study.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
